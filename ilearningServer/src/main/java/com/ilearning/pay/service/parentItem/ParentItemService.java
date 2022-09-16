package com.ilearning.pay.service.parentItem;

import java.util.*;
import javax.validation.*;
import com.ilearning.pay.controller.admin.parentItem.vo.*;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import com.ilearning.common.pojo.PageResult;

/**
 *  Service 接口
 *
 * @author ${table.author}
 */
public interface ParentItemService {

    /**
     * 创建
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParentItem(@Valid ParentItemCreateReqVO createReqVO);

    /**
     * 更新
     *
     * @param updateReqVO 更新信息
     */
    void updateParentItem(@Valid ParentItemUpdateReqVO updateReqVO);

    /**
     * 删除
     *
     * @param id 编号
     */
    void deleteParentItem(Long id);

    /**
     * 获得
     *
     * @param id 编号
     * @return 
     */
    ParentItemDO getParentItem(Long id);

    /**
     * 获得列表
     *
     * @param ids 编号
     * @return 列表
     */
    List<ParentItemDO> getParentItemList(Collection<Long> ids);

    /**
     * 获得分页
     *
     * @param pageReqVO 分页查询
     * @return 分页
     */
    PageResult<ParentItemDO> getParentItemPage(ParentItemPageReqVO pageReqVO);

    /**
     * 获得列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 列表
     */
    List<ParentItemDO> getParentItemList(ParentItemExportReqVO exportReqVO);

}
