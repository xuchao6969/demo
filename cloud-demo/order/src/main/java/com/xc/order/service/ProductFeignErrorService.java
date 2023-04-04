package com.xc.order.service;

import com.xc.order.entity.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignErrorService implements FallbackFactory<FeignProductService> {


    @Override
    public FeignProductService create(Throwable throwable) {
        return new FeignProductService() {
            @Override
            public Product findById(Integer id) {
                //打印堆栈异常
                throwable.printStackTrace();
                Product p = new Product();
                p.setId(-1);
                return p;
            }

            @Override
            public Boolean deduce(Integer productId, Integer count) {
//                throwable.printStackTrace();
                System.out.println("========================");
                System.out.println(productId +"       "+count);
                System.out.println("========================");
                return false;
            }
        };
    }
}
