package com.ilearning.pay.dal.dataobject.order;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.ilearning.common.mybatis.core.dataobject.BaseDO;

/**
 * 支付订单 DO
 *
 * @author ${table.author}
 */
@TableName("pay_order")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * 支付订单编号
     */
    @TableId
    private Long id;
    /**
     * 商户编号
     */
    private Long merchantId;
    /**
     * 应用编号
     */
    private Long appId;
    /**
     * 渠道编号
     */
    private Long channelId;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 商户订单编号
     */
    private String merchantOrderId;
    /**
     * 商品标题
     */
    private String subject;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 异步通知地址
     */
    private String notifyUrl;
    /**
     * 通知商户支付结果的回调状态
     */
    private Integer notifyStatus;
    /**
     * 支付金额，单位：分
     */
    private Long amount;
    /**
     * 渠道手续费，单位：百分比
     */
    private Double channelFeeRate;
    /**
     * 渠道手续金额，单位：分
     */
    private Long channelFeeAmount;
    /**
     * 支付状态
     */
    private Integer status;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 订单失效时间
     */
    private Date expireTime;
    /**
     * 订单支付成功时间
     */
    private Date successTime;
    /**
     * 订单支付通知时间
     */
    private Date notifyTime;
    /**
     * 支付成功的订单拓展单编号
     */
    private Long successExtensionId;
    /**
     * 退款状态
     */
    private Integer refundStatus;
    /**
     * 退款次数
     */
    private Integer refundTimes;
    /**
     * 退款总金额，单位：分
     */
    private Long refundAmount;
    /**
     * 渠道用户编号
     */
    private String channelUserId;
    /**
     * 渠道订单号
     */
    private String channelOrderNo;

}
