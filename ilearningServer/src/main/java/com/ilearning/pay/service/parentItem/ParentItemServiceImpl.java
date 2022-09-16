package com.ilearning.pay.service.parentItem;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ilearning.pay.controller.admin.parentItem.vo.*;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import com.ilearning.common.pojo.PageResult;

import com.ilearning.pay.convert.parentItem.ParentItemConvert;
import com.ilearning.pay.dal.mysql.parentItem.ParentItemMapper;

import static com.ilearning.common.exception.util.ServiceExceptionUtil.exception;
import static com.ilearning.pay.enums.ErrorCodeConstants.*;

/**
 *  Service 实现类
 *
 * @author ${table.author}
 */
@Service
@Validated
public class ParentItemServiceImpl implements ParentItemService {

    @Resource
    private ParentItemMapper parentItemMapper;

    @Override
    public Long createParentItem(ParentItemCreateReqVO createReqVO) {
        // 插入
        ParentItemDO parentItem = ParentItemConvert.INSTANCE.convert(createReqVO);
        parentItemMapper.insert(parentItem);
        // 返回
        return parentItem.getId();
    }

    @Override
    public void updateParentItem(ParentItemUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateParentItemExists(updateReqVO.getId());
        // 更新
        ParentItemDO updateObj = ParentItemConvert.INSTANCE.convert(updateReqVO);
        parentItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteParentItem(Long id) {
        // 校验存在
        this.validateParentItemExists(id);
        // 删除
        parentItemMapper.deleteById(id);
    }

    private void validateParentItemExists(Long id) {
        if (parentItemMapper.selectById(id) == null) {
            throw exception(PARENT_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public ParentItemDO getParentItem(Long id) {
        return parentItemMapper.selectById(id);
    }

    @Override
    public List<ParentItemDO> getParentItemList(Collection<Long> ids) {
        return parentItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ParentItemDO> getParentItemPage(ParentItemPageReqVO pageReqVO) {
        return parentItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ParentItemDO> getParentItemList(ParentItemExportReqVO exportReqVO) {
        return parentItemMapper.selectList(exportReqVO);
    }

}
