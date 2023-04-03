package com.xmr.config;

import com.xmr.interceptor.MyInterceptor1;
import com.xmr.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xmr on 2019/8/1.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        //排除路径
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/hello");
        super.addInterceptors(registry);
    }

//    @Override
//    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
//        //处理 callable超时
//        configurer.setDefaultTimeout(60 * 1000);
////        configurer.registerCallableInterceptors(timeoutInterceptor());
//        configurer.setTaskExecutor(getAsyncThreadPoolTaskExecutor);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }
}
