package com.ilearning.pay.dal.mysql.parent;

import java.util.*;

import com.ilearning.common.pojo.PageResult;
import com.ilearning.common.mybatis.core.query.LambdaQueryWrapperX;
import com.ilearning.common.mybatis.core.mapper.BaseMapperX;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;
import org.apache.ibatis.annotations.Mapper;
import com.ilearning.pay.controller.admin.parent.vo.*;

/**
 *  Mapper
 *
 * @author ${table.author}
 */
@Mapper
public interface ParentMapper extends BaseMapperX<ParentDO> {

    default PageResult<ParentDO> selectPage(ParentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParentDO>()
                .eqIfPresent(ParentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParentDO::getUserId, reqVO.getUserId())
                .orderByDesc(ParentDO::getId));
    }

    default List<ParentDO> selectList(ParentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ParentDO>()
                .eqIfPresent(ParentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParentDO::getUserId, reqVO.getUserId())
                .orderByDesc(ParentDO::getId));
    }

}
