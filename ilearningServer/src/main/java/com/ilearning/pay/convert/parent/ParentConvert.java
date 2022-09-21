package com.ilearning.pay.convert.parent;

import java.util.*;

import com.ilearning.common.pojo.PageResult;

import com.ilearning.pay.dal.dataobject.parent.ParentDO2;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ilearning.pay.controller.admin.parent.vo.*;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;

/**
 *  Convert
 *
 * @author ${table.author}
 */
@Mapper
public interface ParentConvert {

    ParentConvert INSTANCE = Mappers.getMapper(ParentConvert.class);

    ParentDO convert(ParentCreateReqVO bean);

    ParentDO convert(ParentUpdateReqVO bean);

    ParentRespVO convert(ParentDO bean);

    List<ParentRespVO> convertList(List<ParentDO> list);

    PageResult<ParentRespVO> convertPage(PageResult<ParentDO> page);


    PageResult<ParentRespVO> convertPage2(PageResult<ParentDO2> page);

    List<ParentExcelVO> convertList02(List<ParentDO> list);

}
