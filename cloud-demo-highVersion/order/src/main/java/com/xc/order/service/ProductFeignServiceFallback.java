package com.xc.order.service;

import com.xc.order.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignServiceFallback implements FeignProductService {
    @Override
    public Product findById(Integer id) {
        Product p = new Product();
        p.setId(-1);
        return p;
    }

    @Override
    public Boolean deduce(Integer productId, Integer count) {
        return false;
    }
}
