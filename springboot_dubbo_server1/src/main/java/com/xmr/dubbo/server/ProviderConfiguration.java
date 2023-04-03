package com.xmr.dubbo.server;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by xmr on 2020/2/21.
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.xmr.dubbo.server")
@PropertySource("classpath:dubbo.properties")
public class ProviderConfiguration {
}
