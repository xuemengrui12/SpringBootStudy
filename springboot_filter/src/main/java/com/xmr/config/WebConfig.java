package com.xmr.config;

import com.xmr.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by xmr on 2019/8/1.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {




    /**
     * 使用代码注册Filter
     * 将过滤器添加到过滤器链中
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        registration.setOrder(2);
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
        registration.addUrlPatterns("/hello");
        return registration;
    }
//
//    /**
//     * 注册监听器为 Bean
//     * @return
//     */
//    @Bean
//    public ServletListenerRegistrationBean<MyServletContextListener> servletContextListenerRegistrationBean() {
//        return new ServletListenerRegistrationBean<>(new MyServletContextListener());
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean<MyHttpSessionListener> httpSessionListenerRegistrationBean() {
//        return new ServletListenerRegistrationBean<>(new MyHttpSessionListener());
//    }
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
////        registry.addInterceptor(new MyInterceptor());
//        super.addInterceptors(registry);
//    }
//    //    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
////        //处理 callable超时
////        configurer.setDefaultTimeout(60*1000);
//////        configurer.registerCallableInterceptors(timeoutInterceptor());
////        configurer.setTaskExecutor(getAsyncThreadPoolTaskExecutor);
////    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
//        super.addResourceHandlers(registry);
//    }
}
