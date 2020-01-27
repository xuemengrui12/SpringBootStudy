package com.xmr.config;

import com.xmr.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by xmr on 2019/8/1.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new MyInterceptor());
        super.addInterceptors(registry);
    }

    //        @Override
//        public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
//        //处理 callable超时
//        configurer.setDefaultTimeout(60*1000);
////        configurer.registerCallableInterceptors(timeoutInterceptor());
//        configurer.setTaskExecutor(getAsyncThreadPoolTaskExecutor);
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }
}
