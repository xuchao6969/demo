package com.xc.user.service;

import com.xc.user.dao.UserDao;
import com.xc.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public Map<String, Object> deduceMoney(Integer id, String money){

        Optional<User> u = dao.findById(id);
        User us = u.get();
        us.setMoney(us.getMoney().subtract(new BigDecimal(money)));
        dao.save(us);
        Map<String, Object> m = new HashMap<>();
        m.put("code", "1");
//        System.out.println(1/0);
        return m;
    }
}
