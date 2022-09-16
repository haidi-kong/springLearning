package com.ilearning.pay.service.parent;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ilearning.pay.controller.admin.parent.vo.*;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;
import com.ilearning.common.pojo.PageResult;

import com.ilearning.pay.convert.parent.ParentConvert;
import com.ilearning.pay.dal.mysql.parent.ParentMapper;

import static com.ilearning.common.exception.util.ServiceExceptionUtil.exception;
import static com.ilearning.pay.enums.ErrorCodeConstants.*;

/**
 *  Service 实现类
 *
 * @author ${table.author}
 */
@Service
@Validated
public class ParentServiceImpl implements ParentService {

    @Resource
    private ParentMapper parentMapper;

    @Override
    @Transactional
    public Long createParent(ParentCreateReqVO createReqVO) {
        // 插入
        ParentDO parent = ParentConvert.INSTANCE.convert(createReqVO);
        parentMapper.insert(parent);
        // 返回
        return parent.getId();
    }

    @Override
    public void updateParent(ParentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateParentExists(updateReqVO.getId());
        // 更新
        ParentDO updateObj = ParentConvert.INSTANCE.convert(updateReqVO);
        parentMapper.updateById(updateObj);
    }

    @Override
    public void deleteParent(Long id) {
        // 校验存在
        this.validateParentExists(id);
        // 删除
        parentMapper.deleteById(id);
    }

    private void validateParentExists(Long id) {
        if (parentMapper.selectById(id) == null) {
            throw exception(PARENT_NOT_EXISTS);
        }
    }

    @Override
    public ParentDO getParent(Long id) {
        return parentMapper.selectById(id);
    }

    @Override
    public List<ParentDO> getParentList(Collection<Long> ids) {
        return parentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ParentDO> getParentPage(ParentPageReqVO pageReqVO) {
        return parentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ParentDO> getParentList(ParentExportReqVO exportReqVO) {
        return parentMapper.selectList(exportReqVO);
    }

}
