package com.xc.shop.dao;

import com.xc.shop.entity.Product;
import com.xc.shop.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}
