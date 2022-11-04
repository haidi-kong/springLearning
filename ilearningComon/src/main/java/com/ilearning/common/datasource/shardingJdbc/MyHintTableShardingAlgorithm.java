package com.ilearning.common.datasource.shardingJdbc;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyHintTableShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Comparable<?>> shardingValue) {
        String key = shardingValue.getLogicTableName() + "_" + shardingValue.getValues().toArray()[0];
        Integer curValue = (Integer) shardingValue.getValues().toArray()[0];
        int databaseKey = curValue % 2;
        return Arrays.asList(shardingValue.getLogicTableName() + "_" + + databaseKey);
    }

}