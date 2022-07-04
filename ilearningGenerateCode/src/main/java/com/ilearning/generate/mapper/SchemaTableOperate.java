package com.ilearning.generate.mapper;

import com.ilearning.generate.dataObject.SchemaColumnDO;
import com.ilearning.generate.dataObject.SchemaTableDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemaTableOperate {

    /**
     * 从数据库中，获得数据库表结构
     */
     List<SchemaColumnDO> selectColumnByTableName(@Param("tableName") String tableName);

}
