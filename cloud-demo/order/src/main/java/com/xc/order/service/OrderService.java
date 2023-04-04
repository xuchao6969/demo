package com.xc.order.service;

import com.xc.order.dao.OrderDao;
import com.xc.order.entity.Order;
import com.xc.order.entity.Product;
import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderDao dao;

    @Autowired
    private FeignProductService feignProductService;

    @Autowired
    private FeignUserService feignUserService;

    public  void save(Order order){
        dao.save(order);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    public Order createrOrder(Integer id, Integer cou){
        Product p = feignProductService.findById(id);
        Order o = new Order();
        o.setProductId(p.getId());
        o.setUserId(1);
        o.setCount(cou);
        o.setAmount(p.getPrice().multiply(new BigDecimal(String.valueOf(cou))));
        dao.save(o);
        Boolean flag = feignProductService.deduce(id, o.getCount());
        if (flag){
            Map<String, Object> result = feignUserService.deduceMoney(1, o.getAmount().toString());
            String code = (String) result.get("code");
            if ("1".equals(code)){
                System.out.println("~~~~~~~~~~~~成功~~~~~~~~~~");
            }else{
                System.out.println("======== user-server扣款异常 =========");
                throw new RuntimeException((Throwable) result.get("error"));
            }

        }else{
            System.out.println("==========  shop-server库存异常 =========");
            throw new RuntimeException("shop-server异常");
        }
        System.out.println(1/0);
        return o;
    }

}
