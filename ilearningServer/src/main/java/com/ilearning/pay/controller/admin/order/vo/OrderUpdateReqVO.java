package com.ilearning.pay.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("用户 APP - 支付订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderUpdateReqVO extends OrderBaseVO {

    @ApiModelProperty(value = "支付订单编号", required = true)
    @NotNull(message = "支付订单编号不能为空")
    private Long id;

}
