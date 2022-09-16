package com.ilearning.pay.controller.admin.parentItem.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("用户 APP -  Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParentItemRespVO extends ParentItemBaseVO {

    @ApiModelProperty(value = "", required = true)
    private Long id;

}
