package com.xmr.controller;

import com.xmr.config.MyEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @Author: Administrator
 * @Date: 2022/6/26
 * @LastEditTime: 2022/6/26 22:13
 * @LastEditors: Administrator
 * @Description:
 */
@RestController
@RequestMapping("/system")
public class MyEnvironmentController implements EnvironmentAware {
    @Value("${spring.datasource.url}") // 使用el表达式读取spring主配置文件
    private String jdbcUrl;

    private String java_home;
    private Logger logger = Logger.getLogger(MyEnvironmentController.class.getName());

    @RequestMapping("/javahome")
    public String getJAVAHOME() {
        return java_home;
    }

    @Override
    public void setEnvironment(Environment environment) {
        java_home = environment.getProperty("java_home");
        logger.info("控制器中获取的系统环境变量：" + java_home);
    }
}