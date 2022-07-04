package com.ilearning.generate.dataObject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * MySQL 数据库中的 column 字段定义
 *
 * @author ilearning
 */
@Data
@Builder
public class SchemaColumnDO {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     * 是否允许为空 YES / NO
     */
    private String isNullable;
    /**
     * 是否主键 PRI
     */
    private String columnKey;
    /**
     * 是否自增
     */
    private String extra;
    /**
     * 排序字段
     */
    private Integer ordinalPosition;

}
