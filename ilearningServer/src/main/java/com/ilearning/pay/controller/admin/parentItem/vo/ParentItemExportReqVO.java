package com.ilearning.pay.controller.admin.parentItem.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.ilearning.common.pojo.PageParam;

@ApiModel(value = "用户 APP -  Excel 导出 Request VO", description = "参数和 ParentItemPageReqVO 是一致的")
@Data
public class ParentItemExportReqVO {

    @ApiModelProperty(value = "")
    private Long orderId;

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer userId;

}
