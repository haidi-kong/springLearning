package com.ilearning.pay.controller.admin.parent.vo;

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
public class ParentExcelVO {

    @ExcelProperty("")
    private Long id;

    @ExcelProperty("")
    private String status;

    @ExcelProperty("")
    private Integer userId;

}
