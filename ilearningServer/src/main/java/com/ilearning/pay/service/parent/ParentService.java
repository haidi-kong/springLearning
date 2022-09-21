package com.ilearning.pay.service.parent;

import java.util.*;
import javax.validation.*;
import com.ilearning.pay.controller.admin.parent.vo.*;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;
import com.ilearning.common.pojo.PageResult;
import com.ilearning.pay.dal.dataobject.parent.ParentDO2;

/**
 *  Service 接口
 *
 * @author ${table.author}
 */
public interface ParentService {

    /**
     * 创建
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParent(@Valid ParentCreateReqVO createReqVO);

    /**
     * 更新
     *
     * @param updateReqVO 更新信息
     */
    void updateParent(@Valid ParentUpdateReqVO updateReqVO);

    /**
     * 删除
     *
     * @param id 编号
     */
    void deleteParent(Long id);

    /**
     * 获得
     *
     * @param id 编号
     * @return 
     */
    ParentDO getParent(Long id);

    /**
     * 获得列表
     *
     * @param ids 编号
     * @return 列表
     */
    List<ParentDO> getParentList(Collection<Long> ids);

    /**
     * 获得分页
     *
     * @param pageReqVO 分页查询
     * @return 分页
     */
    PageResult<ParentDO> getParentPage(ParentPageReqVO pageReqVO);


    PageResult<ParentDO2> getParentPageDetail(ParentPageReqVO pageReqVO);

    /**
     * 获得列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 列表
     */
    List<ParentDO> getParentList(ParentExportReqVO exportReqVO);

}
