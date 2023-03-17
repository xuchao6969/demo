package com.xc.optimisticlock.controller;

import com.xc.optimisticlock.service.NumberSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private NumberSourceService service;

    @RequestMapping("/test")
    public void test(int did, int uid){
        service.register(did, uid);
    }


}
