package com.xc.optimisticlock.mapper;

import com.xc.optimisticlock.entity.NumberSource;
import com.xc.optimisticlock.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NumberSourceMapper {
    /**
     * 查询号源库存
     * @param id 商品id
     * @return
     */
    NumberSource getNumberSource(@Param("id") int id);


    /**
     * 乐观锁方案扣减库存
     * @param id 医生id
     * @param version 版本号
     * @return
     */
    int decreaseNumberSourceStockByIdAndVersion(@Param("id") int id, @Param("version") int version);



    /**
     * 保存订单
     * @param order
     */
    int insertOrder(Order order);
}
