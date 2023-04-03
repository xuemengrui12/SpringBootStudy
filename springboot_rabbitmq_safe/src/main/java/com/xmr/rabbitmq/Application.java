package com.xmr.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.xmr.rabbitmq.dao")
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    }

}
