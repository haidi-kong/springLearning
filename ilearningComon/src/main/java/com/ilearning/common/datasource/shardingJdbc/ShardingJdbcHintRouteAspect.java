package com.ilearning.common.datasource.shardingJdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@Aspect
@Order(-10)
@Component
@Slf4j
public class ShardingJdbcHintRouteAspect {

    @Value("${spring.shardingsphere.sharding.binding-tables}")
    private String shardingTables;
    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码 使用@Pointcut声明切入点表达式 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
    @Pointcut("execution(public * com.ilearning.*.controller..*Controller.*(..))")
    public void declearJoinPointExpression() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    public void beforMethod(JoinPoint joinPoint) {
        // Hint分片策略必须要使用 HintManager工具类
        HintManager hintManager = HintManager.getInstance();
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String headValue = "";
        if (sra != null) {
            headValue  = sra.getRequest().getHeader("tenantId");
        }

        log.info("sharding table value {}", shardingTables);
        if (StringUtils.isNotBlank(shardingTables)) {
            String[] tables = shardingTables.split(",");
            for (String table : tables) {
                hintManager.addDatabaseShardingValue(table, headValue);
                hintManager.addTableShardingValue(table, headValue);
            }
        }
    }

    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     *
     * @param joinPoint
     */
    @After("declearJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("After---------------");
        HintManager.clear();
    }

    /**
     * 返回通知（在方法正常结束执行的代码） 返回通知可以访问到方法的返回值！
     *
     * @param joinPoint
     */
    public void afterReturnMethod(JoinPoint joinPoint, Object result) {

    }

    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数) 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法 且环绕通知必须有返回值，返回值即目标方法的返回值
     *
     * @param joinPoint
     */
    @Around(value = "declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        // 前置通知
        beforMethod(joinPoint);

        // 执行目标方法
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        // 后置通知 - 新建
        afterReturnMethod(joinPoint, result);
        return result;
    }
}
