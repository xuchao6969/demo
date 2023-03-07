package com.xc.springtransaction;

import com.xc.springtransaction.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTransactionApplication {

    public static void main(String[] args) {
        //获取springboot启动后spring 应用配置的上下文
        ConfigurableApplicationContext ca =
        SpringApplication.run(SpringTransactionApplication.class, args);
        TransactionService uc = (TransactionService) ca.getBean("transactionService");
        uc.test16();
    }


}
