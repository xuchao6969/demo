package com.xc.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xc.order.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Autowired
    private SentinelService service;

    @RequestMapping("/msg1")
    public String msg1(){
        service.msg1();
        return "msg1";
    }

    @RequestMapping("/msg2")
    public String msg2(){
        service.msg1();
        return "msg2";
    }

    @RequestMapping("/msg3")
    @SentinelResource("msg3")
    public String msg2(String name, String age){
//        service.msg1();
        return name+age;
    }

}
