package com.ilearning.pay.controller.admin.parent.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.ilearning.common.pojo.PageParam;

@ApiModel(value = "用户 APP -  Excel 导出 Request VO", description = "参数和 ParentPageReqVO 是一致的")
@Data
public class ParentExportReqVO {

    @ApiModelProperty(value = "")
    private String status;

    @ApiModelProperty(value = "")
    private Integer userId;

}
