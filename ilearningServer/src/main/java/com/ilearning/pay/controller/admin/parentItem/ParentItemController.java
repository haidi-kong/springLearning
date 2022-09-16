package com.ilearning.pay.controller.admin.parentItem;

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

import com.ilearning.pay.controller.admin.parentItem.vo.*;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import com.ilearning.pay.convert.parentItem.ParentItemConvert;
import com.ilearning.pay.service.parentItem.ParentItemService;

@Api(tags = "用户 APP - 分库分表子表")
@RestController
@RequestMapping("/pay/parent-item")
@Validated
public class ParentItemController {

    @Resource
    private ParentItemService parentItemService;

    @PostMapping("/create")
    @ApiOperation("创建")

    public CommonResult<Long> createParentItem(@Valid @RequestBody ParentItemCreateReqVO createReqVO) {
        return success(parentItemService.createParentItem(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新")

    public CommonResult<Boolean> updateParentItem(@Valid @RequestBody ParentItemUpdateReqVO updateReqVO) {
        parentItemService.updateParentItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)

    public CommonResult<Boolean> deleteParentItem(@RequestParam("id") Long id) {
        parentItemService.deleteParentItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<ParentItemRespVO> getParentItem(@RequestParam("id") Long id) {
        ParentItemDO parentItem = parentItemService.getParentItem(id);
        return success(ParentItemConvert.INSTANCE.convert(parentItem));
    }

    @GetMapping("/list")
    @ApiOperation("获得列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<ParentItemRespVO>> getParentItemList(@RequestParam("ids") Collection<Long> ids) {
        List<ParentItemDO> list = parentItemService.getParentItemList(ids);
        return success(ParentItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分页")

    public CommonResult<PageResult<ParentItemRespVO>> getParentItemPage(@Valid ParentItemPageReqVO pageVO) {
        PageResult<ParentItemDO> pageResult = parentItemService.getParentItemPage(pageVO);
        return success(ParentItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 Excel")

    @OperateLog(type = EXPORT)
    public void exportParentItemExcel(@Valid ParentItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ParentItemDO> list = parentItemService.getParentItemList(exportReqVO);
        // 导出 Excel
        List<ParentItemExcelVO> datas = ParentItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, ".xls", "数据", ParentItemExcelVO.class, datas);
    }

}
