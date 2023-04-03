package com.xmr;

import com.xmr.domain.Hello;
import com.xmr.domain.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xmr on 2018/4/2.
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnClass(Hello.class)
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloAutoConfiguration {
    @Autowired
    private HelloProperties helloServiceProperties;

    @Bean
    @ConditionalOnMissingBean(Hello.class)
    public Hello instantHello() {
        Hello hello = new Hello();
        hello.setMsg(helloServiceProperties.getMsg());
        return hello;
    }

}
