package com.ilearning.pay.service.order;

import java.util.*;
import javax.validation.*;
import com.ilearning.pay.controller.admin.order.vo.*;
import com.ilearning.pay.dal.dataobject.order.OrderDO;
import com.ilearning.common.pojo.PageResult;

/**
 * 支付订单 Service 接口
 *
 * @author ${table.author}
 */
public interface OrderService {

    /**
     * 创建支付订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid OrderCreateReqVO createReqVO);

    /**
     * 更新支付订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderUpdateReqVO updateReqVO);

    /**
     * 删除支付订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得支付订单
     *
     * @param id 编号
     * @return 支付订单
     */
    OrderDO getOrder(Long id);

    /**
     * 获得支付订单列表
     *
     * @param ids 编号
     * @return 支付订单列表
     */
    List<OrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得支付订单分页
     *
     * @param pageReqVO 分页查询
     * @return 支付订单分页
     */
    PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO);

    /**
     * 获得支付订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 支付订单列表
     */
    List<OrderDO> getOrderList(OrderExportReqVO exportReqVO);

}
