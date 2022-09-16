package com.ilearning.pay.controller.admin.parent;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ilearning.common.pojo.PageResult;
import com.ilearning.common.pojo.CommonResult;
import static com.ilearning.common.pojo.CommonResult.success;

import com.ilearning.common.util.excel.ExcelUtils;

import com.ilearning.common.annotations.OperateLog;
import static com.ilearning.common.enums.OperateTypeEnum.*;

import com.ilearning.pay.controller.admin.parent.vo.*;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;
import com.ilearning.pay.convert.parent.ParentConvert;
import com.ilearning.pay.service.parent.ParentService;

@Api(tags = "用户 APP - 分库分表父表")
@RestController
@RequestMapping("/pay/parent")
@Validated
public class ParentController {

    @Resource
    private ParentService parentService;

    @PostMapping("/create")
    @ApiOperation("创建")

    public CommonResult<Long> createParent(@Valid @RequestBody ParentCreateReqVO createReqVO) {
        return success(parentService.createParent(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新")

    public CommonResult<Boolean> updateParent(@Valid @RequestBody ParentUpdateReqVO updateReqVO) {
        parentService.updateParent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)

    public CommonResult<Boolean> deleteParent(@RequestParam("id") Long id) {
        parentService.deleteParent(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<ParentRespVO> getParent(@RequestParam("id") Long id) {
        ParentDO parent = parentService.getParent(id);
        return success(ParentConvert.INSTANCE.convert(parent));
    }

    @GetMapping("/list")
    @ApiOperation("获得列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<ParentRespVO>> getParentList(@RequestParam("ids") Collection<Long> ids) {
        List<ParentDO> list = parentService.getParentList(ids);
        return success(ParentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分页")

    public CommonResult<PageResult<ParentRespVO>> getParentPage(@Valid ParentPageReqVO pageVO) {
        PageResult<ParentDO> pageResult = parentService.getParentPage(pageVO);
        return success(ParentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 Excel")

    @OperateLog(type = EXPORT)
    public void exportParentExcel(@Valid ParentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ParentDO> list = parentService.getParentList(exportReqVO);
        // 导出 Excel
        List<ParentExcelVO> datas = ParentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, ".xls", "数据", ParentExcelVO.class, datas);
    }

}