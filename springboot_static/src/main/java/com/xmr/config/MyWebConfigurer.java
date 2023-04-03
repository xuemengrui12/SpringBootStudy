package com.xmr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by xmr on 2020/3/26.
 */
@Configuration
public class MyWebConfigurer extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
// 访问myres根目录下的pic.jpg 的URL为 http://localhost:8080/pic.jpg （/** 会覆盖系统默认的配置）
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/myres/")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
