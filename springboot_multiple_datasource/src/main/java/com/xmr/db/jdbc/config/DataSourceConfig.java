package com.xmr.db.jdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by xmr on 2020/1/13.
 */
@Configuration
public class DataSourceConfig {
    //主数据源配置
    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    //主数据源
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


//    //第二个数据源配置
//    @Bean(name = "secondaryDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSourceProperties secondaryDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    //第二个数据源
//    @Bean("secondaryDataSource")
//    public DataSource secondaryDataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
//        return dataSourceProperties.initializeDataSourceBuilder().build();
//    }

    /**
     * 两种实现方式
     *
     * @return
     */
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
