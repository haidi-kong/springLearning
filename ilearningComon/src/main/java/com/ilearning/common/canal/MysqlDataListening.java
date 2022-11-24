package com.ilearning.common.canal;

import com.ilearning.common.redis.RedisKeyDefine;
import com.ilearning.common.redis.RedisKeyRegistry;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class MysqlDataListening {

    private static final ThreadFactory springThreadFactory = new CustomizableThreadFactory("canal-pool-");

    private static final ExecutorService executors = Executors.newFixedThreadPool(1, springThreadFactory);

    @Autowired
    private CanalInstanceProperties canalInstanceProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @PostConstruct
    private void startListening() {
        canalInstanceProperties.getInstance().forEach(
            instanceName -> {
                executors.submit(() -> {
                    connector(instanceName);
                });
            }
        );
    }

    /**
     * 消费canal的线程池
     */
    public void connector(String instance){
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(canalInstanceProperties.getServerAddress(), canalInstanceProperties.getServerPort()),
                instance, "", "");
        canalConnector.connect();
        //订阅所有消息
        canalConnector.subscribe(".*\\..*");
        // canalConnector.subscribe("test1.*"); 只订阅test1数据库下的所有表
        //恢复到之前同步的那个位置
        canalConnector.rollback();

        for(;;){
            //获取指定数量的数据，但是不做确认标记，下一次取还会取到这些信息。 注：不会阻塞，若不够100，则有多少返回多少
            Message message = canalConnector.getWithoutAck(100);
            //获取消息id
            long batchId = message.getId();
            int size = message.getEntries().size();
            if (size == 0 || batchId == -1) {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
            if(batchId != -1){
                log.info("instance -> {}, msgId -> {}", instance, batchId);
                printEnity(message.getEntries());
                //提交确认
                canalConnector.ack(batchId);
                //处理失败，回滚数据
                //canalConnector.rollback(batchId);
            }
        }
    }

    private  void printEnity(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChange = null;
            try{
                // 序列化数据
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            assert rowChange != null;
            CanalEntry.EventType eventType = rowChange.getEventType();
            log.info(String.format("================>; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                     eventType));

            if (rowChange.getEventType() == CanalEntry.EventType.QUERY || rowChange.getIsDdl()) {
                log.info("sql ------------>{}" ,rowChange.getSql());
            }

            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    switch (rowChange.getEventType()){
                        //如果希望监听多种事件，可以手动增加case
                        case UPDATE:
                            printColumn(rowData.getAfterColumnsList());
                            printColumn(rowData.getBeforeColumnsList());
                            handleRedisCache(entry.getHeader().getTableName(), rowData.getAfterColumnsList());
                            break;
                        case INSERT:
                            printColumn(rowData.getAfterColumnsList());
                            handleRedisCache(entry.getHeader().getTableName(), rowData.getAfterColumnsList());
                            break;
                        case DELETE:
                            printColumn(rowData.getBeforeColumnsList());
                            handleRedisCache(entry.getHeader().getTableName(), rowData.getAfterColumnsList());
                            break;
                        default:
                    }
                }
        }
    }

    private void printColumn(List<CanalEntry.Column> columns) {
        StringBuilder sb = new StringBuilder();
        for (CanalEntry.Column column : columns) {
            sb.append("[");
            sb.append(column.getName()).append(":").append(column.getValue()).append(",update=").append(column.getUpdated());
            sb.append("]");
            sb.append("    ");
        }
        log.info(sb.toString());
    }

    private void handleRedisCache(String tableName, List<CanalEntry.Column> columns) {
        // 分库分表，需要抽取表前缀
        RedisKeyDefine redisKeyDefine = RedisKeyRegistry.getDefines().get(tableName.substring(0,tableName.length()-2));
        if (redisKeyDefine != null) {
            List<String> keyColumns = new ArrayList<>();
            for (CanalEntry.Column column : columns) {
                if (redisKeyDefine.getTableColumnList().contains(column.getName())) {
                    keyColumns.add(column.getValue());
                }
            }
            String key = String.format(redisKeyDefine.getKeyTemplate(), keyColumns.toArray());
            log.info("delete cache, key is {}", key);
            stringRedisTemplate.delete(key);
        }
    }

}
