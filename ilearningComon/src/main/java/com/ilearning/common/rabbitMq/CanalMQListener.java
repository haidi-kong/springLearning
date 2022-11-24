package com.ilearning.common.rabbitMq;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ilearning.common.redis.RedisKeyDefine;
import com.ilearning.common.redis.RedisKeyRegistry;
import com.ilearning.common.util.json.JsonUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CanalMQListener
 * @Description: 监听mysql 数据变动
 * @Author: LZJ
 * @DATE: 2022/11/20 9:13
 * @Version: v1.0
 */
 
@Component
@Slf4j
public class CanalMQListener {

    private AtomicInteger messageCount = new AtomicInteger(0);
    private AtomicInteger sqlCount = new AtomicInteger(0);;



    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queues = "canal.test.queue")
    public void CanalMessageHandler(Message mqMessage, Channel channel) {
        try {
            //获取指定数量的数据，但是不做确认标记，下一次取还会取到这些信息。 注：不会阻塞，若不够100，则有多少返回多少
            CanalMessage message = JsonUtils.parseObject(new String(mqMessage.getBody(), StandardCharsets.UTF_8),
                    CanalMessage.class);
            //获取消息id
            assert message != null;
            printEnity(message);
        } catch (Exception e) {
            log.error("canal消息处理失败: {}, error info {}", new String(mqMessage.getBody(), StandardCharsets.UTF_8), e.getMessage());
            throw new RuntimeException(e);
        }
 
    }

    private  void printEnity(CanalMessage message) {
        if (EventType.QUERY.getTypeName().equals(message.getType()) || message.getIsDdl()) {
            log.info("sql ------------>{}" ,message.getSql());
        }
        log.info(String.format("================>; message id [%s], name[%s,%s] , eventType : %s, messageCount : %d",
                message.getId(),
                message.getDatabase(), message.getTable(),
                message.getType(), messageCount.addAndGet(1)));
        EventType type = EventType.getEventByTypeName(message.getType());
        if (type == null) {
            log.info ("unknown type {}", message.getType());
            return;
        }
        for (int i = 0; i < message.getData().size(); i++) {
            switch (type){
                //如果希望监听多种事件，可以手动增加case
                case UPDATE:
                    printColumn(message.getData().get(i), message.getOld().get(i));
                    handleRedisCache(message.getTable(), message.getData().get(i));
                    break;
                case INSERT:
                    printColumn(message.getData().get(i), message.getOld().get(i));
                    handleRedisCache(message.getTable(), message.getData().get(i));
                    break;
                case DELETE:
                    printColumn(message.getData().get(i), message.getOld().get(i));
                    handleRedisCache(message.getTable(), message.getData().get(i));
                    break;
                default:
            }
        }
    }

    private void printColumn(Object changeData, Object oldData) {
        String sb =
                "count" + sqlCount.addAndGet(1) + "  " +
                "old data:" + JsonUtils.toJsonString(oldData) +
                "==>" +
                "new data" + JsonUtils.toJsonString(changeData);
        log.info(sb);
    }

    private void handleRedisCache(String tableName, Object changeData) {
        // 分库分表，需要抽取表前缀
        RedisKeyDefine redisKeyDefine = RedisKeyRegistry.getDefines().get(tableName.substring(0,tableName.length()-2));
        if (redisKeyDefine != null) {
            JsonNode jsonNode = JsonUtils.readTree(JsonUtils.toJsonString(changeData));
            List<String> keyColumns = new ArrayList<>();
            Iterator<Map.Entry<String,JsonNode>> it =jsonNode.fields();
            while(it.hasNext()){
                Map.Entry<String,JsonNode> entry=it.next();
                if (redisKeyDefine.getTableColumnList().contains(entry.getKey())) {
                    keyColumns.add(entry.getValue().asText());
                }
            }

            String key = String.format(redisKeyDefine.getKeyTemplate(), keyColumns.toArray());
            log.info("delete cache, key is {}", key);
            stringRedisTemplate.delete(key);
        }
    }
}

