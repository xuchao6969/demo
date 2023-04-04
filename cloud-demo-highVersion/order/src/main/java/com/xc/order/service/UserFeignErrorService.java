package com.xc.order.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserFeignErrorService implements FallbackFactory<FeignUserService> {
    @Override
    public FeignUserService create(Throwable throwable) {
        return new FeignUserService() {
            @Override
            public Map<String, Object> deduceMoney(Integer id, String money) {
                Map<String, Object> m = new HashMap<>();
                m.put("code", "2");
                m.put("error", throwable);
                return m;
            }
        };
    }
}
