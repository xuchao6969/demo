package com.xc.shop.service;

import com.xc.shop.dao.ProductDao;
import com.xc.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao dao;

    public Product findById(Integer id){
        Optional<Product> p = dao.findById(id);
        return p.get();
    }

    public Boolean deduce(Integer id, Integer count) {
        Optional<Product> p = dao.findById(id);
        Product product = p.get();
        /*try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        product.setStock(product.getStock()-count);
        dao.save(product);
        return true;
    }
}
