package com.xmr.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author: Administrator
 * @Date: 2022/7/9
 * @LastEditTime: 2022/7/9 21:07
 * @LastEditors: Administrator
 * @Description:
 */
@Configuration
@EnableCaching//标注启动缓存.
public class CacheConfiguration {
    /**
     * ehcache 主要的管理器
     *
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        System.out.println("CacheConfiguration.ehCacheCacheManager()");
        return new EhCacheCacheManager(bean.getObject());
    }
//    @Bean
//    public EhCacheCacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
//    }
    /*
* 据 shared 与否的设置,
* Spring 分别通过 CacheManager.create()
* 或 new CacheManager()方式来创建一个 ehcache 基地.
*
* 也说是说通过这个来设置 cache 的基地是这里的 Spring 独用,还是跟别的(如 hibernate 的Ehcache 共享)
*
*/
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation
                (new ClassPathResource("/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
