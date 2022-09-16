package com.ilearning.pay.controller.admin.parentItem.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("用户 APP - 更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParentItemUpdateReqVO extends ParentItemBaseVO {

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private Long id;

}
