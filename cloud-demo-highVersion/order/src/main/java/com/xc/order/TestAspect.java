package com.xc.order;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class TestAspect {
    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Before("execution(* com.xc.order.service.*.*(..))")
    public void before(JoinPoint joinPoint) throws TransactionException {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("拦截到需要分布式事务的方法," + method.getName());
        // 此处可用redis或者定时任务来获取一个key判断是否需要关闭分布式事务
        // 模拟动态关闭分布式事务
//        if ((int)(Math.random() * 100) % 2 == 0) {
//            GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
//            tx.begin(300000, "test-client");
//        } else {
//            logger.info("关闭分布式事务");
//        }
    }

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.xc.order.service.*.*(..))")
    public void doRecoveryActions(Throwable e) throws TransactionException {
        logger.info("方法执行异常:{}", e.getMessage());
        System.out.println(RootContext.getXID() + "~~~~~~~~~~~~~");
        if (StringUtils.hasText(RootContext.getXID())){
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();

        }

    }

    @AfterReturning(value = "execution(* com.xc.order.service.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint point, Object result) throws TransactionException {
        logger.info("方法执行结束:{}", result);
        if (StringUtils.hasText(RootContext.getXID())){
                logger.info("分布式事务Id:{}", RootContext.getXID());
//                GlobalTransactionContext.reload(RootContext.getXID()).commit();
            }
    }


}