package com.ilearning.pay.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("用户 APP - 支付订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderRespVO extends OrderBaseVO {

    @ApiModelProperty(value = "支付订单编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
