// ========== 支付订单 TODO 补充编号 ==========
package com.ilearning.pay.enums;


import com.ilearning.common.exception.ErrorCode;
public interface ErrorCodeConstants {
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1009000002, "支付订单不存在");
}