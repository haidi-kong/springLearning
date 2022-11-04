package com.ilearning.common.datasource.shardingJdbc;

import com.ilearning.common.util.collection.CollectionUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class MyComplexTableShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        Collection<Comparable<?>> cidRange = shardingValue.getColumnNameAndShardingValuesMap().get("update_time");
        Collection<Comparable<?>> userIdCol = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");

        List<String> res = new ArrayList<>();

        for(Object userId: userIdCol){

            //course_{userID%2+1}
        }

        return Collections.singleton("pay_parent_0");
    }

}