package com.ilearning.pay.controller.admin.parentItem.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.ilearning.common.pojo.PageParam;

@ApiModel("用户 APP - 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParentItemPageReqVO extends PageParam {

    @ApiModelProperty(value = "")
    private Long orderId;

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer userId;

}
