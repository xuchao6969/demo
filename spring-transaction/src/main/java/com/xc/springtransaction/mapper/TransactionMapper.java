package com.xc.springtransaction.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TransactionMapper {
    void insertFoo(@Param("id") String id, @Param("fooName") String fooName);

    void updateFoo(@Param("id") String id, @Param("fooName") String fooName);

    void insertBar(@Param("id") String id, @Param("barName") String barName);

    void updateBar(@Param("id") String id, @Param("barName") String barName);
}
