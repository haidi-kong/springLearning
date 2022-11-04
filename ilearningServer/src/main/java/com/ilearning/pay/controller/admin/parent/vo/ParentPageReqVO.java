package com.ilearning.pay.controller.admin.parent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.ilearning.common.pojo.PageParam;

@ApiModel("用户 APP - 分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParentPageReqVO extends PageParam {

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer userId;

    @ApiModelProperty(value = "")
    private String updateTime;

}
