package com.ilearning.pay.controller.admin.parentItem.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 *  Excel VO
 *
 * @author ${table.author}
 */
@Data
public class ParentItemExcelVO {

    @ExcelProperty("")
    private Long id;

    @ExcelProperty("")
    private Long orderId;

    @ExcelProperty("")
    private String status;

    @ExcelProperty("")
    private Integer userId;

}
