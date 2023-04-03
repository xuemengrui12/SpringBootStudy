package com.xmr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xml.service.HelloService;

import javax.annotation.Resource;

/**
 * @Author: Administrator
 * @Date: 2022/7/3
 * @LastEditTime: 2022/7/3 18:17
 * @LastEditors: Administrator
 * @Description:
 */
@Component
@Order(value = 1)
public class TestXMLBeanRunner implements CommandLineRunner {
    @Resource
    private HelloService helloService;
    @Override
    public void run(String... arg0) {
        helloService.hello();
    }
}
