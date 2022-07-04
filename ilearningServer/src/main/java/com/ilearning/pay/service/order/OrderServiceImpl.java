package com.ilearning.pay.service.order;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ilearning.pay.controller.admin.order.vo.*;
import com.ilearning.pay.dal.dataobject.order.OrderDO;
import com.ilearning.common.pojo.PageResult;

import com.ilearning.pay.convert.order.OrderConvert;
import com.ilearning.pay.dal.mysql.order.OrderMapper;

import static com.ilearning.common.exception.util.ServiceExceptionUtil.exception;
import static com.ilearning.pay.enums.ErrorCodeConstants.*;

/**
 * 支付订单 Service 实现类
 *
 * @author ${table.author}
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public Long createOrder(OrderCreateReqVO createReqVO) {
        // 插入
        OrderDO order = OrderConvert.INSTANCE.convert(createReqVO);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(OrderUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = OrderConvert.INSTANCE.convert(updateReqVO);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        this.validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<OrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderDO> getOrderList(OrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

}
