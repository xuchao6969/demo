package com.xc.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {

    @SentinelResource("msg")
    public void msg1(){
        System.out.println("msg");
    }
}
