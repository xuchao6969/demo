package com.xc.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.xc.order.entity.Order;
import com.xc.order.entity.Product;
import com.xc.order.service.FeignProductService;
import com.xc.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignProductService feignProductService;
//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping("/order/product/{id}")
    public String test(@PathVariable("id") Integer id){

//        ServiceInstance serviceInstance = discoveryClient.getInstances("shop-server").get(0);
//        String ip = serviceInstance.getHost()+":"+serviceInstance.getPort();
//        logger.info("ip===={}", ip);
        String ip = "shop-server";
//        Product p = restTemplate.getForObject("http://"+ip+"/product/"+id, Product.class);
        Product p = feignProductService.findById(id);
        if (p.getId() == -1){
            return "下单失败";
        }
        Order o = new Order();
        o.setProductId(p.getId());
        o.setUserId(1);
        o.setCount(2);
        o.setAmount(new BigDecimal("2000.00"));
        service.save(o);
        return JSONObject.toJSONString(p);
    }

    @GetMapping("/order/createrOrder")
    public void createrOrder(Integer id, Integer cou){
        service.createrOrder(id, cou);
    }
}
