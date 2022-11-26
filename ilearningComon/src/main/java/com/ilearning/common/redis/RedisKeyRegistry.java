package com.ilearning.common.redis;

import com.ilearning.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RedisKeyDefine} 注册表
 */
@Slf4j
public class RedisKeyRegistry {

    /**
     * Redis RedisKeyDefine 数组
     */
    private static final Map<String, RedisKeyDefine> definesMap = new HashMap<>();

    public static void add(RedisKeyDefine define) {
        log.info("redisKeyDefine add {}", JsonUtils.toJsonString(define));
        definesMap.put(define.getTable(), define);
    }

    public static  Map<String, RedisKeyDefine> getDefines() {
        return definesMap;
    }

    public static int size() {
        return definesMap.size();
    }

}
