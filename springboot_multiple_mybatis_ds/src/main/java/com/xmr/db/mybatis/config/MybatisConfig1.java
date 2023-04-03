package com.xmr.db.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by xmr on 2020/1/14.
 */
@MapperScan(basePackages ="com.xmr.db.mybatis.mapper.ds1", sqlSessionTemplateRef  = "primarySqlSessionTemplate")
@Configuration
public class MybatisConfig1 {
    //主数据源
    @Primary
    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                //指定不同的映射文件使用具体哪个数据源
                getResources("classpath*:mybatis/mapper/ds1/*.xml"));
        return sqlSessionFactory.getObject();
    }

    /**
     * 事务支持
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    @ConditionalOnBean(SqlSessionFactoryBean.class) // 当 SqlSessionFactoryBean 实例存在时创建对象
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.light.springboot.mapper");
//        return mapperScannerConfigurer;
//
//    }
}
