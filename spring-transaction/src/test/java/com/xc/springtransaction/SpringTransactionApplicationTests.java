package com.xc.springtransaction;

import com.xc.springtransaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootTest
class SpringTransactionApplicationTests {

    @Resource
    ApplicationContext applicationContext;

    @Test
    void transactionTest() {

        TransactionService uc = (TransactionService) applicationContext.getBean("transactionService");
        uc.test16();

    }

}
