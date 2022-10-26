package com.ilearning.common.datasource.shardingJdbc;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DatabasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        Integer curValue = shardingValue.getValue();
        int databaseKey = curValue % 2;
        return "demo" + databaseKey;
    }
}