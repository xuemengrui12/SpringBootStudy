package com.xmr.config;

import com.xmr.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by xmr on 2019/8/1.
 */
@Configuration
public class WebConfig {


    /**
     * 修改DispatcherServlet默认配置
     * 当然，这里可以对DispatcherServlet做很多修改，并非只是UrlMappings
     *
     * @param dispatcherServlet
     * @return
     */
//    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("*.do");
        registration.addUrlMappings("*.json");
        return registration;
    }

    /**
     * 使用代码注册Servlet（不需要@ServletComponentScan注解）
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new MyServlet(), "/hello");// ServletName默认值为首字母小写，即myServlet
    }

}
