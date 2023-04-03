package org.xml.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @Author: Administrator
 * @Date: 2022/7/3
 * @LastEditTime: 2022/7/3 18:13
 * @LastEditors: Administrator
 * @Description:
 */
@Service("helloService")
public class HelloService {
    private Logger logger = Logger.getLogger(HelloService.class.getName());

    public void hello() {
        logger.info("这个bean是springboot默认情况下无法扫描到的");
    }
}