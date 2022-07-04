package com.ilearning.generate.dataObject;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * MySQL 数据库中的 table 表定义
 *
 * @author ilearning
 */
@Data
@Builder
public class SchemaTableDO {

    /**
     * 数据库
     */
    private String tableSchema;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 创建时间
     */
    private Date createTime;

}
