package com.ilearning.pay.controller.admin.parent.vo;

import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
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

    @ApiModelProperty(value = "订单详情", required = true)
    private List<ParentItemDO> parentItemDOList;

}
