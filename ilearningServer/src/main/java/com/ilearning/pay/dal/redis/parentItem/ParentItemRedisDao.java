package com.ilearning.pay.dal.redis.parentItem;

import com.ilearning.common.util.json.JsonUtils;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;
import com.ilearning.pay.dal.redis.RedisKeyConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ParentItemRedisDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    public ParentItemDO get(ParentItemDO parentItemDO) {
        String redisKey = formatKey(parentItemDO.getId());
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), ParentItemDO.class);
    }

    public ParentItemDO getById(Long id) {
        String redisKey = formatKey(id);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), ParentItemDO.class);
    }

    public List<ParentItemDO> getByIdList(Collection<Long> ids) {
        List<ParentItemDO> cacheList = new ArrayList<>();
        for (Long id : ids) {
            ParentItemDO parentItemDOCache = getById(id);
            if (parentItemDOCache != null) {
                cacheList.add(parentItemDOCache);
            }
        }
        return cacheList;
    }

    public void set(ParentItemDO parentItemDO) {
        if (parentItemDO == null) {
            return;
        }
        String redisKey = formatKey(parentItemDO.getId());
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(parentItemDO), RedisKeyConstants.PAY_PARENT_ITEM.getTimeout());
    }

    public void delete(ParentItemDO parentItemDO) {
        String redisKey = formatKey(parentItemDO.getId());
        stringRedisTemplate.delete(redisKey);
    }

    private static String formatKey(Long itemId) {
        return String.format(RedisKeyConstants.PAY_PARENT_ITEM.getKeyTemplate(), itemId);
    }
}
