package com.xc.demo.service;

import com.xc.demo.mapper.TransactionMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
public class TransactionService {
    @Autowired
    private TransactionMapper mapper;

    /**
     * condition: saveTrueWithoutTransactionAnnotation 不发生异常 没有事务注解 this调用
     * condition: saveFalseWithoutTransactionAnnotation 发生异常 没有事务注解 this调用
     * conclusion: 事务生效 两个子方法全部执行失败  整个事务回滚
     */
    @Transactional
    public void test1() {
        this.saveTrueWithoutTransactionAnnotation();
        this.saveFalseWithoutTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithoutTransactionAnnotation 不发生异常 没有事务注解 代理调用
     * condition: saveFalseWithoutTransactionAnnotation 发生异常 没有事务注解 代理调用
     * conclusion: 事务生效 两个子方法全部执行失败  整个事务回滚
     */
    @Transactional
    public void test2() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithoutTransactionAnnotation();
        ts.saveFalseWithoutTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别requires_new this调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别requires_new this调用
     * conclusion: 外层事务生效 两个子方法全部执行失败 整个事务回滚
     */
    @Transactional
    public void test3() {
        this.saveTrueWithTransactionAnnotation();
        this.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别requires_new 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别requires_new 代理调用
     * conclusion: 外层事务失效 saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation执行失败，事务回滚
     */
    @Transactional
    public void test4() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        ts.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别requires_new this调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别requires_new this调用
     * conclusion: saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation执行错误，该方法第一行执行成功，事务并未回滚
     */
    public void test5() {
        this.saveTrueWithTransactionAnnotation();
        this.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别requires_new 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别requires_new 代理调用
     * conclusion: saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation执行失败，事务回滚
     */
    public void test6() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        ts.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required this调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别required this调用
     * conclusion: 外层事务生效 两个子方法全部执行失败 整个事务回滚
     */
    @Transactional
    public void test7() {
        this.saveTrueWithTransactionAnnotation();
        this.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别required 代理调用
     * conclusion: 外层事务生效 两个子方法全部执行失败 整个事务回滚
     */
    @Transactional
    public void test8() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        ts.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required this调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别required this调用
     * conclusion: saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation执行错误，该方法第一行执行成功，事务并未回滚
     */
    public void test9() {
        this.saveTrueWithTransactionAnnotation();
        this.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别required 代理调用
     * conclusion: saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation执行失败，事务回滚
     */
    public void test10() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        ts.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别supports 代理调用
     * conclusion: 外部事务生效 saveFalseWithTransactionAnnotation执行失败，事务回滚
     */
    @Transactional
    public void test11(){
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveFalseWithTransactionAnnotation();
    }

    /**
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别supports 代理调用
     * conclusion: saveFalseWithTransactionAnnotation执行错误，第一行执行成功，事务并未回滚
     */
    public void test12(){
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveFalseWithTransactionAnnotation();
    }


    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别nested 代理调用
     * conclusion: saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation的事务回滚
     */
    @Transactional
    public void test13(){
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        try {
            ts.saveFalseWithTransactionAnnotation();
        } catch (Exception e) { System.out.println("捕获异常信息"); }
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别required 代理调用
     * conclusion: 外部事务成功，两个子事务全部执行失败，整体回滚
     */
    @Transactional
    public void test14(){
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        try {
            ts.saveFalseWithTransactionAnnotation();
        } catch (Exception e) { System.out.println("捕获异常信息"); }
    }

    /**
     * condition: saveTrueWithTransactionAnnotation 不发生异常 有事务注解 隔离级别required 代理调用
     * condition: saveFalseWithTransactionAnnotation 发生异常 有事务注解 隔离级别requires_new 代理调用
     * conclusion: 外部事务成功，saveTrueWithTransactionAnnotation执行成功，事务提交；saveFalseWithTransactionAnnotation的事务回滚
     */
    @Transactional
    public void test15(){
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrueWithTransactionAnnotation();
        try {
            ts.saveFalseWithTransactionAnnotation();
        } catch (Exception e) { System.out.println("捕获异常信息"); }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTrueWithTransactionAnnotation(){
        mapper.insertFoo("a", "foo1");
        mapper.insertBar("b","bar1");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFalseWithTransactionAnnotation(){
        mapper.updateFoo("1", "fff");
        System.out.println(1/0);
        mapper.updateBar("1","bbb");
    }

    public void saveTrueWithoutTransactionAnnotation(){
        mapper.insertFoo("a", "foo1");
        mapper.insertBar("b","bar1");
    }

    public void saveFalseWithoutTransactionAnnotation(){
        mapper.updateFoo("1", "fff");
        System.out.println(1/0);
        mapper.updateBar("1","bbb");
    }
}
