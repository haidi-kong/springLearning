package com.ilearning.pay.controller.admin.order;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.*;

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

import com.ilearning.pay.controller.admin.order.vo.*;
import com.ilearning.pay.dal.dataobject.order.OrderDO;
import com.ilearning.pay.convert.order.OrderConvert;
import com.ilearning.pay.service.order.OrderService;

@Api(tags = "用户 APP - 支付订单")
@RestController
@RequestMapping("/pay/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建支付订单")

    public CommonResult<Long> createOrder(@Valid @RequestBody OrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新支付订单")

    public CommonResult<Boolean> updateOrder(@Valid @RequestBody OrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除支付订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)

    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得支付订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<OrderRespVO> getOrder(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @ApiOperation("获得支付订单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<OrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderDO> list = orderService.getOrderList(ids);
        return success(OrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得支付订单分页")

    public CommonResult<PageResult<OrderRespVO>> getOrderPage(@Valid OrderPageReqVO pageVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(pageVO);
        return success(OrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出支付订单 Excel")

    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid OrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<OrderExcelVO> datas = OrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "支付订单.xls", "数据", OrderExcelVO.class, datas);
    }

}
