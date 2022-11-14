package com.ilearning.common.datasource.shardingJdbc;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class MyHintDatabaseShardingAlgorithm implements HintShardingAlgorithm<Comparable<?>> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Comparable<?>> shardingValue) {
        int curValue = Integer.parseInt((String) shardingValue.getValues().toArray()[0]);
        int databaseKey = curValue % 2;
        for (String dataBase : availableTargetNames) {
            if (dataBase.contains(String.valueOf(databaseKey))) {
                return Collections.singletonList(dataBase);
            }
        }
        return new ArrayList<>();
    }

}