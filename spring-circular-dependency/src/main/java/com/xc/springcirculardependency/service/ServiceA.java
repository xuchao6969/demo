package com.xc.springcirculardependency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceA {

    @Autowired
    private ServiceB serviceB;

    public void testA(){
        serviceB.testB();
    }
}
