package com.xc.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.xc.shop.entity.Product;
import com.xc.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService service;

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable("id") Integer id){
        Product p = service.findById(id);
        logger.info("---{}", JSONObject.toJSONString(p));
        return p;
    }
    @RequestMapping("/product/deduce")
    public Boolean deduce(Integer id, Integer count){
        return service.deduce(id, count);
    }
}
