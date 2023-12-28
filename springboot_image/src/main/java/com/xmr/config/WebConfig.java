package xmr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Administrator
 * @Date: 26/8/2023
 * @LastEditTime: 26/8/2023 下午 12:43
 * @LastEditors: Administrator
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**") //虚拟url路径
                .addResourceLocations("file:F:/image/"); //真实本地路径
//        registry.addResourceHandler("/crmebimage/**") //虚拟url路径
//                .addResourceLocations("file:F:/crmebimage/"); //真实本地路径
//        registry.addResourceHandler("/crmebimage/**") //虚拟url路径
//                .addResourceLocations("file:F:/Gitee/crmeb_java/crmeb/crmebimage/"); //真实本地路径

    }
}
