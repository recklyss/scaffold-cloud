package com.cms.scaffold.code.config.micro;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang
 * 声明式事务配置
 */
@Configuration
public class TransactionConfig {

    @Bean("txSource")
    public TransactionAttributeSource transactionAttributeSource() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        // 只读事务，不做更新操作
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        // 默认级别 如果有事务 就加入当前事务 没有就新建
        RuleBasedTransactionAttribute requiredTx =
                new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        // 事务新建 不管有没有事务 都新建事务再执行
        RuleBasedTransactionAttribute requiredTxNew =
                new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRES_NEW,
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setTimeout(5);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("merge*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("do*", requiredTx);
        txMap.put("handle*", requiredTx);
        // 以下为只读事务 PROPAGATION_NOT_SUPPORTED级别为不开启事务
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("count*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("load*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        txMap.put("*", readOnlyTx);
        source.setNameMap(txMap);
        return source;
    }

    /**
     * 切面拦截规则 参数会自动从容器中注入
     */
    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor) {
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txInterceptor);
        // 拦截微服务提供者的controller包 注入事务拦截控制
        pointcutAdvisor.setExpression("execution (* com.cms.scaffold.micro.*.controller.*.*(..))");
        return pointcutAdvisor;
    }

    /**
     * 事务拦截器
     */
    @Bean("txInterceptor")
    TransactionInterceptor txInterceptor(PlatformTransactionManager tx) {
        return new TransactionInterceptor(tx, transactionAttributeSource());
    }
}
