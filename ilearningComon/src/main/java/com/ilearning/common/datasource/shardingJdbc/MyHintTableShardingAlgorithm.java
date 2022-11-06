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
        int curValue = Integer.parseInt((String) shardingValue.getValues().toArray()[0]);
        int databaseKey = curValue % 2;
        return Collections.singletonList(shardingValue.getLogicTableName() + "_" + +databaseKey);
    }

}