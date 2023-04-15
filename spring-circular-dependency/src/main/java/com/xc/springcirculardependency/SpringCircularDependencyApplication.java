package com.xc.springcirculardependency;

import com.xc.springcirculardependency.service.ServiceA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringCircularDependencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCircularDependencyApplication.class, args);

    }

}
