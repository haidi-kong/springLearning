package com.ilearning.pay.dal.dataobject.parent;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.ilearning.common.mybatis.core.dataobject.BaseDO;

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
public class ParentDO extends BaseDO {

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

}
