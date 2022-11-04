package com.ilearning.common.datasource.shardingJdbc;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class MyHintDatabaseShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Comparable<?>> shardingValue) {
        Integer curValue = (Integer) shardingValue.getValues().toArray()[0];
        int databaseKey = curValue % 2;
        return Arrays.asList("demo" + databaseKey);
    }

}