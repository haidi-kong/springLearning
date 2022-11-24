package com.ilearning.pay.dal.redis;

import com.ilearning.common.redis.RedisKeyDefine;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import io.swagger.v3.oas.models.PathItem;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class RedisKeyConstants {

    /**
     * 支付子表缓存 key 为表的表的分布式主键
     *
     */
    public static final RedisKeyDefine PAY_PARENT_ITEM = new RedisKeyDefine("支付子表缓存",
            "pay_parent_item:%s",
            RedisKeyDefine.KeyTypeEnum.STRING, ParentItemDO.class,
            Duration.ofHours(1), "pay_parent_item", Collections.singletonList("id"));

}
