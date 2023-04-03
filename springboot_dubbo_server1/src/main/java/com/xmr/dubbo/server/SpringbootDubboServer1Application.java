package com.xmr.dubbo.server;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
public class SpringbootDubboServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboServer1Application.class, args);
    }

}
