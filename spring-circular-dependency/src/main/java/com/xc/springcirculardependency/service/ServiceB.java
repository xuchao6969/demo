package com.xc.springcirculardependency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void testB(){
    }

}
