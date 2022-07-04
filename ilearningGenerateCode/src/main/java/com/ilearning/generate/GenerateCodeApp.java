package com.ilearning.generate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ZipUtil;
import com.ilearning.generate.config.CodegenProperties;
import com.ilearning.generate.dataObject.CodegenColumnDO;
import com.ilearning.generate.dataObject.CodegenTableDO;
import com.ilearning.generate.dataObject.SchemaColumnDO;
import com.ilearning.generate.dataObject.SchemaTableDO;
import com.ilearning.generate.file.GenFileUtil;
import com.ilearning.generate.mapper.SchemaOperate;
import com.ilearning.generate.mapper.SchemaTableOperate;
import com.ilearning.generate.velocity.CodegenBuilder;
import com.ilearning.generate.velocity.CodegenEngine;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GenerateCodeApp {

    public static final String GEN_PATH_KEY = "gen.fileRootPath";

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory("mybatis-config.xml");
        CodegenEngine codegenEngine = new CodegenEngine();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SchemaOperate schemaOperate = session.getMapper(SchemaOperate.class);
            // 获取数据库表结构
            List<SchemaTableDO> schemaTableDOList = schemaOperate.selectByTableSchema();
            // 获取每张表对应字段信息
            for (SchemaTableDO schemaTableDO : schemaTableDOList) {
                System.out.println(schemaTableDO);
                SchemaTableOperate schemaTableOperate = session.getMapper(SchemaTableOperate.class);
                List<SchemaColumnDO> schemaColumnDOList = schemaTableOperate.selectColumnByTableName(schemaTableDO.getTableName());
                List<CodegenColumnDO> codegenColumnDOList = new ArrayList<>();
                for(SchemaColumnDO schemaColumnDO : schemaColumnDOList) {
                    codegenColumnDOList.add(CodegenBuilder.schemaColumnDOToCodegenColumnDO(schemaColumnDO));
                    System.out.println(schemaColumnDO);
                }
                CodegenTableDO codegenTableDO = CodegenBuilder.buildTable(schemaTableDO);
                // 调用模板代码生成
                Map<String, String> map =  codegenEngine.execute(codegenTableDO, codegenColumnDOList);
                System.out.println(map);
                for (String path : map.keySet()) {
                    File file = new File(CodegenProperties.getValue(GEN_PATH_KEY)+path);
                    boolean isCreatedNew =  GenFileUtil.createFile(file);
                    // 使用FileWriter写文件
                    if (isCreatedNew) {
                        try (FileWriter writer = new FileWriter(file)) {
                            writer.write(map.get(path));
                        }
                    }

                }
            }



        }
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        // 数据源配置
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://42.192.183.193:3306/ilearning?useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "lzj2515628");
        properties.setProperty("password", "lzj2515628");
        // 获取数据源
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        //
        configuration.addMappers("com.ilearning.generate.mapper");
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private static SqlSessionFactory getSqlSessionFactory(String resource) throws IOException {
        // 数据源配置
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
