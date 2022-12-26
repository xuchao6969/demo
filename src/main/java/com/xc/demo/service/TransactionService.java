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
     * 全部失败 事务生效 同一个事务中
     */
    @Transactional
    public void test() {
        this.saveTrue();
        this.saveFalse();
    }

    /**
     * 全部失败 事务生效 同一个事务中
     */
    @Transactional
    public void test1() {
        TransactionService ts = (TransactionService) AopContext.currentProxy();
        ts.saveTrue();
        ts.saveFalse();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTrue(){
        mapper.insertFoo("a", "foo1");
        mapper.insertBar("b","bar1");
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFalse(){
        mapper.updateFoo("1", "fff");
        System.out.println(1/0);
        mapper.updateBar("1","bbb");
    }

}
