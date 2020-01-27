package com.xmr.config;

import com.xmr.listener.MyHttpSessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by xmr on 2019/8/1.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * 注册监听器为 Bean
     *
     * @return
     */
//    @Bean
//    public ServletListenerRegistrationBean<MyServletContextListener> servletContextListenerRegistrationBean() {
//        return new ServletListenerRegistrationBean<>(new MyServletContextListener());
//    }

    @Bean
    public ServletListenerRegistrationBean<MyHttpSessionListener> httpSessionListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new MyHttpSessionListener());
    }
}
