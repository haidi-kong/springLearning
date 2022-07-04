package com.ilearning.pay.dal.mysql.order;

import java.util.*;

import com.ilearning.common.pojo.PageResult;
import com.ilearning.common.mybatis.core.query.LambdaQueryWrapperX;
import com.ilearning.common.mybatis.core.mapper.BaseMapperX;
import com.ilearning.pay.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.ilearning.pay.controller.admin.order.vo.*;

/**
 * 支付订单 Mapper
 *
 * @author ${table.author}
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(OrderDO::getAppId, reqVO.getAppId())
                .eqIfPresent(OrderDO::getChannelId, reqVO.getChannelId())
                .eqIfPresent(OrderDO::getChannelCode, reqVO.getChannelCode())
                .eqIfPresent(OrderDO::getMerchantOrderId, reqVO.getMerchantOrderId())
                .eqIfPresent(OrderDO::getSubject, reqVO.getSubject())
                .eqIfPresent(OrderDO::getBody, reqVO.getBody())
                .eqIfPresent(OrderDO::getNotifyUrl, reqVO.getNotifyUrl())
                .eqIfPresent(OrderDO::getNotifyStatus, reqVO.getNotifyStatus())
                .eqIfPresent(OrderDO::getAmount, reqVO.getAmount())
                .eqIfPresent(OrderDO::getChannelFeeRate, reqVO.getChannelFeeRate())
                .eqIfPresent(OrderDO::getChannelFeeAmount, reqVO.getChannelFeeAmount())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderDO::getUserIp, reqVO.getUserIp())
                .betweenIfPresent(OrderDO::getExpireTime, reqVO.getBeginExpireTime(), reqVO.getEndExpireTime())
                .betweenIfPresent(OrderDO::getSuccessTime, reqVO.getBeginSuccessTime(), reqVO.getEndSuccessTime())
                .betweenIfPresent(OrderDO::getNotifyTime, reqVO.getBeginNotifyTime(), reqVO.getEndNotifyTime())
                .eqIfPresent(OrderDO::getSuccessExtensionId, reqVO.getSuccessExtensionId())
                .eqIfPresent(OrderDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderDO::getRefundTimes, reqVO.getRefundTimes())
                .eqIfPresent(OrderDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(OrderDO::getChannelUserId, reqVO.getChannelUserId())
                .eqIfPresent(OrderDO::getChannelOrderNo, reqVO.getChannelOrderNo())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(OrderDO::getId));
    }

    default List<OrderDO> selectList(OrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(OrderDO::getAppId, reqVO.getAppId())
                .eqIfPresent(OrderDO::getChannelId, reqVO.getChannelId())
                .eqIfPresent(OrderDO::getChannelCode, reqVO.getChannelCode())
                .eqIfPresent(OrderDO::getMerchantOrderId, reqVO.getMerchantOrderId())
                .eqIfPresent(OrderDO::getSubject, reqVO.getSubject())
                .eqIfPresent(OrderDO::getBody, reqVO.getBody())
                .eqIfPresent(OrderDO::getNotifyUrl, reqVO.getNotifyUrl())
                .eqIfPresent(OrderDO::getNotifyStatus, reqVO.getNotifyStatus())
                .eqIfPresent(OrderDO::getAmount, reqVO.getAmount())
                .eqIfPresent(OrderDO::getChannelFeeRate, reqVO.getChannelFeeRate())
                .eqIfPresent(OrderDO::getChannelFeeAmount, reqVO.getChannelFeeAmount())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderDO::getUserIp, reqVO.getUserIp())
                .betweenIfPresent(OrderDO::getExpireTime, reqVO.getBeginExpireTime(), reqVO.getEndExpireTime())
                .betweenIfPresent(OrderDO::getSuccessTime, reqVO.getBeginSuccessTime(), reqVO.getEndSuccessTime())
                .betweenIfPresent(OrderDO::getNotifyTime, reqVO.getBeginNotifyTime(), reqVO.getEndNotifyTime())
                .eqIfPresent(OrderDO::getSuccessExtensionId, reqVO.getSuccessExtensionId())
                .eqIfPresent(OrderDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderDO::getRefundTimes, reqVO.getRefundTimes())
                .eqIfPresent(OrderDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(OrderDO::getChannelUserId, reqVO.getChannelUserId())
                .eqIfPresent(OrderDO::getChannelOrderNo, reqVO.getChannelOrderNo())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(OrderDO::getId));
    }

}
