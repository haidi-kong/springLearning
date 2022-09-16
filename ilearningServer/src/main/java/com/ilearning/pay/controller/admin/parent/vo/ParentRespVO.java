package com.ilearning.pay.controller.admin.parent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("用户 APP -  Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParentRespVO extends ParentBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Long id;

}
