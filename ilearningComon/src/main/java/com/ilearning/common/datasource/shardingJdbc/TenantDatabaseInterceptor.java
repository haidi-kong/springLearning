package com.ilearning.common.datasource.shardingJdbc;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 基于 MyBatis Plus 多租户的功能，实现 DB 层面的多租户的功能
 *
 */
@AllArgsConstructor
public class TenantDatabaseInterceptor implements TenantLineHandler {

    private final TenantProperties properties;

    @Override
    public Expression getTenantId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String headValue = "";
        if (sra != null) {
            headValue  = sra.getRequest().getHeader("tenantId");
        }
        return new StringValue(headValue);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return CollUtil.contains(properties.getIgnoreTables(), tableName); // 情况二，忽略多租户的表
    }

}
