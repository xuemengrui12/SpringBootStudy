package com.xmr.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication app =new SpringApplication(Application.class);
        app.addListeners(new MyApplicationStartingEventListener());//加入自定义的监听类
        app.run(args);
    }

}
