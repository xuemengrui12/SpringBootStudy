package com.xmr.db.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 *使用默认数据源
 * Created by xmr on 2020/1/14.
 */
@Configuration
public class MybatisConfig {

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        System.out.println("============" + dataSource.getClass());
        // 设置mybatis的主配置文件
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        // 设置mapper映射文件
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mappers/*.xml"));
        // 设置别名包
        sqlSessionFactory.setTypeAliasesPackage("com.xmr.db.mybatis.domain");
        return sqlSessionFactory.getObject();
    }

    @Bean
    @ConditionalOnBean(SqlSessionFactoryBean.class) // 当 SqlSessionFactoryBean 实例存在时创建对象
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xmr.db.mybatis.dao");
        return mapperScannerConfigurer;

    }
}
