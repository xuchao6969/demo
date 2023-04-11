package com.xc.springaop;

import com.xc.springaop.service.AopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringAopApplication {

    public static void main(String[] args) {
        ApplicationContext a = SpringApplication.run(SpringAopApplication.class, args);
        AopService s = (AopService) a.getBean("aopService");
        s.test();
    }

}
