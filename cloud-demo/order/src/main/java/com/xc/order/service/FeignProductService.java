package com.xc.order.service;

import com.xc.order.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "shop-server", fallback = ProductFeignServiceFallback.class)
@FeignClient(value = "shop-server", fallbackFactory = ProductFeignErrorService.class)
//@FeignClient(value = "shop-server")
public interface FeignProductService {

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable("id") Integer id);

    @RequestMapping("/product/deduce")
    public Boolean deduce(@RequestParam("id") Integer productId, @RequestParam("count") Integer count);
}
