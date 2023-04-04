package com.xc.user.controller;

import com.xc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/user/money")
    public Map<String, Object> deduceMoney(Integer id, String money){
        return service.deduceMoney(id, money);
    }
}
