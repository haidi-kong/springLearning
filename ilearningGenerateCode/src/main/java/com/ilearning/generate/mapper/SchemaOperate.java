package com.ilearning.generate.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilearning.generate.dataObject.SchemaTableDO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface SchemaOperate {

    /**
     * 从数据库中，获得数据库表结构
     */
     List<SchemaTableDO> selectByTableSchema();

}
