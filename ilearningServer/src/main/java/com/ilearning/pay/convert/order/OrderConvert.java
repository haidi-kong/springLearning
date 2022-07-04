package com.ilearning.pay.convert.order;

import java.util.*;

import com.ilearning.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ilearning.pay.controller.admin.order.vo.*;
import com.ilearning.pay.dal.dataobject.order.OrderDO;

/**
 * 支付订单 Convert
 *
 * @author ${table.author}
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderDO convert(OrderCreateReqVO bean);

    OrderDO convert(OrderUpdateReqVO bean);

    OrderRespVO convert(OrderDO bean);

    List<OrderRespVO> convertList(List<OrderDO> list);

    PageResult<OrderRespVO> convertPage(PageResult<OrderDO> page);

    List<OrderExcelVO> convertList02(List<OrderDO> list);

}
