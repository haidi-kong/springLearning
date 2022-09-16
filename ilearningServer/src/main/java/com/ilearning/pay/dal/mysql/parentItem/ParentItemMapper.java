package com.ilearning.pay.dal.mysql.parentItem;

import java.util.*;

import com.ilearning.common.pojo.PageResult;
import com.ilearning.common.mybatis.core.query.LambdaQueryWrapperX;
import com.ilearning.common.mybatis.core.mapper.BaseMapperX;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import org.apache.ibatis.annotations.Mapper;
import com.ilearning.pay.controller.admin.parentItem.vo.*;

/**
 *  Mapper
 *
 * @author ${table.author}
 */
@Mapper
public interface ParentItemMapper extends BaseMapperX<ParentItemDO> {

    default PageResult<ParentItemDO> selectPage(ParentItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParentItemDO>()
                .eqIfPresent(ParentItemDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ParentItemDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParentItemDO::getUserId, reqVO.getUserId())
                .orderByDesc(ParentItemDO::getId));
    }

    default List<ParentItemDO> selectList(ParentItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ParentItemDO>()
                .eqIfPresent(ParentItemDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ParentItemDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParentItemDO::getUserId, reqVO.getUserId())
                .orderByDesc(ParentItemDO::getId));
    }

}
