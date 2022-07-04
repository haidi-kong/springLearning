package com.ilearning.pay.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 支付订单 Excel VO
 *
 * @author ${table.author}
 */
@Data
public class OrderExcelVO {

    @ExcelProperty("支付订单编号")
    private Long id;

    @ExcelProperty("商户编号")
    private Long merchantId;

    @ExcelProperty("应用编号")
    private Long appId;

    @ExcelProperty("渠道编号")
    private Long channelId;

    @ExcelProperty("渠道编码")
    private String channelCode;

    @ExcelProperty("商户订单编号")
    private String merchantOrderId;

    @ExcelProperty("商品标题")
    private String subject;

    @ExcelProperty("商品描述")
    private String body;

    @ExcelProperty("异步通知地址")
    private String notifyUrl;

    @ExcelProperty("通知商户支付结果的回调状态")
    private Integer notifyStatus;

    @ExcelProperty("支付金额，单位：分")
    private Long amount;

    @ExcelProperty("渠道手续费，单位：百分比")
    private Double channelFeeRate;

    @ExcelProperty("渠道手续金额，单位：分")
    private Long channelFeeAmount;

    @ExcelProperty("支付状态")
    private Integer status;

    @ExcelProperty("用户 IP")
    private String userIp;

    @ExcelProperty("订单失效时间")
    private Date expireTime;

    @ExcelProperty("订单支付成功时间")
    private Date successTime;

    @ExcelProperty("订单支付通知时间")
    private Date notifyTime;

    @ExcelProperty("支付成功的订单拓展单编号")
    private Long successExtensionId;

    @ExcelProperty("退款状态")
    private Integer refundStatus;

    @ExcelProperty("退款次数")
    private Integer refundTimes;

    @ExcelProperty("退款总金额，单位：分")
    private Long refundAmount;

    @ExcelProperty("渠道用户编号")
    private String channelUserId;

    @ExcelProperty("渠道订单号")
    private String channelOrderNo;

    @ExcelProperty("创建时间")
    private Date createTime;

}
