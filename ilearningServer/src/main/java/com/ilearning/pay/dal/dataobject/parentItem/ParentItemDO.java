package com.ilearning.pay.dal.dataobject.parentItem;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.ilearning.common.mybatis.core.dataobject.BaseDO;

/**
 *  DO
 *
 * @author ${table.author}
 */
@TableName("pay_parent_item")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentItemDO extends BaseDO {

    /**
     * 
     */
    @TableId
    private Long id;
    /**
     * 
     */
    private Long orderId;
    /**
     * 
     */
    private String status;
    /**
     * 
     */
    private Integer userId;

}
