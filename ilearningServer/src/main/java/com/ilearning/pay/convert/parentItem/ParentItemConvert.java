package com.ilearning.pay.convert.parentItem;

import java.util.*;

import com.ilearning.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ilearning.pay.controller.admin.parentItem.vo.*;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;

/**
 *  Convert
 *
 * @author ${table.author}
 */
@Mapper
public interface ParentItemConvert {

    ParentItemConvert INSTANCE = Mappers.getMapper(ParentItemConvert.class);

    ParentItemDO convert(ParentItemCreateReqVO bean);

    ParentItemDO convert(ParentItemUpdateReqVO bean);

    ParentItemRespVO convert(ParentItemDO bean);

    List<ParentItemRespVO> convertList(List<ParentItemDO> list);

    PageResult<ParentItemRespVO> convertPage(PageResult<ParentItemDO> page);

    List<ParentItemExcelVO> convertList02(List<ParentItemDO> list);

}
