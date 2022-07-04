package com.ilearning.pay.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("用户 APP - 支付订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderCreateReqVO extends OrderBaseVO {

}
