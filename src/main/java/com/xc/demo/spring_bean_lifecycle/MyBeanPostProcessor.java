package com.xc.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userBean".equals(beanName)) {
            System.out.println("8. 调用 BeanPostProcessor.postProcessBeforeInitialization() 方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userBean".equals(beanName)) {
            System.out.println("11. 调用 BeanPostProcessor.postProcessAfterInitialization() 方法");
        }
        return bean;
    }
}
