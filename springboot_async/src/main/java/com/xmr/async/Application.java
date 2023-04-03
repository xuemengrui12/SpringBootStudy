package com.xmr.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;

//@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConcurrentHashMap s;
        SpringApplication.run(Application.class, args);
    }

}
