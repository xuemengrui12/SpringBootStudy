package com.xmr.config;

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
@Configuration
public class MyEnvironment implements EnvironmentAware {
    @Value("${spring.datasource.url}") // 使用el表达式读取spring主配置文件
    private String jdbcUrl;
    private Logger logger = Logger.getLogger(MyEnvironment.class.getName());


    @Override
    public void setEnvironment(Environment environment) {
// springEL表达式获取的值
        logger.info("springel表达式获取的值：" + jdbcUrl);
// 获取系统属性：
        logger.info("JAVA_HOME" + environment.getProperty("JAVA_HOME"));
// 获取spring主配置文件中的属性
        logger.info("spring.datasource.url:" + environment.getProperty("spring.datasource.url"));
// 获取前缀是“spring.datasource”的所有属性值
    }

}