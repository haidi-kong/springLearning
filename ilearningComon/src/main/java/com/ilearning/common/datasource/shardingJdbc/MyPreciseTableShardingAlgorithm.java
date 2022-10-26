package com.ilearning.common.datasource.shardingJdbc;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyPreciseTableShardingAlgorithm  implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        Integer curValue = shardingValue.getValue();
        int tableKey = curValue % 2;
        for (String targetTable : availableTargetNames) {
            if (targetTable.contains(Integer.toString(tableKey))){
                return targetTable;
            }
        }
        throw new RuntimeException("bot find tableKey");
    }

}