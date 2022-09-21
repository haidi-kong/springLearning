package com.ilearning.pay.dal.dataobject.parent;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ilearning.common.mybatis.core.dataobject.BaseDO;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import lombok.*;

import java.util.List;

/**
 *  DO
 *
 * @author ${table.author}
 */
@TableName("pay_parent")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentDO2 extends BaseDO {

    /**
     * 
     */
    @TableId
    private Long id;
    /**
     * 
     */
    private String status;
    /**
     * 
     */
    private Integer userId;

    private List<ParentItemDO> parentItemDOList;

}
