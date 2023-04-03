package com.xmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ServletComponentScan // 开始servlet扫描
@ImportResource(locations = {"applicationContext.xml"})  // 导 入 spring 配置文件
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 配置文件上传
//    @Bean
//    public MultipartConfigElement multipartConfigFactory() {
//        MultipartConfigFactory configFactory = new MultipartConfigFactory();
//        configFactory.setMaxFileSize(DataSize.ofBytes(1024*124));// KB MB 设置单个上传文件大小
//        configFactory.setMaxRequestSize(DataSize.ofBytes(1024*124));
//        configFactory.setLocation("/");// 设置文件上传路径
//        return configFactory.createMultipartConfig();
//    }
}
