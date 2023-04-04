package com.xc.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "user-server", fallbackFactory = UserFeignErrorService.class)
public interface FeignUserService {

    @RequestMapping("/user/money")
    public Map<String, Object> deduceMoney(@RequestParam("id") Integer id, @RequestParam("money") String money);
}
