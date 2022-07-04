package com.ilearning.common.datasource.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


//@Configuration
//@ImportResource({"classpath:applicationContext.xml"})
public class TxAnoConfig {

  
    @Autowired
    private DataSource dataSource;

    @Bean("txManager")
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /*事务拦截器*/
    @Bean("txAdvice")
    public TransactionInterceptor txAdvice(DataSourceTransactionManager txManager){
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
          /*只读事务，不做更新操作*/
         RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
         readOnlyTx.setReadOnly(true);
         readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
         RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
             Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
         requiredTx.setTimeout(5);
         Map<String, TransactionAttribute> txMap = new HashMap<>();
         txMap.put("add*", requiredTx);
         txMap.put("save*", requiredTx);
         txMap.put("insert*", requiredTx);
         txMap.put("update*", requiredTx);
         txMap.put("delete*", requiredTx);
         txMap.put("get*", readOnlyTx);
         txMap.put("query*", readOnlyTx);
         txMap.put("select*", requiredTx);
         txMap.put("*", readOnlyTx);
         source.setNameMap( txMap );
        return new TransactionInterceptor(txManager ,source) ;
    }
 
    /**切面拦截规则 参数会自动从容器中注入*/
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor txAdvice){
        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
        pointcutAdvisor.setAdvice(txAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution (* com.ilearning.pay.service.order.*.*(..))");
        pointcutAdvisor.setPointcut(pointcut);
        return pointcutAdvisor;
    }
 
 
}
