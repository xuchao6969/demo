package com.xc.springaop.component;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAspect {


    @Pointcut("execution(* com.xc.springaop.service.AopService.test())")
    public void pointCutTest(){}

    @Before("pointCutTest()")
    public void BeforeTest(){
        System.out.println("~~~~~~~~~~ before ~~~~~~~");
    }

    @AfterReturning("pointCutTest()")
    public void AfterReturningTest(){
        System.out.println("~~~~~~~~~~~ after returning ~~~~~~~~~~~~~");
    }

}
