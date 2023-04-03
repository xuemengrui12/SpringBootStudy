package com.xmr.db.mybatis.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by xmr on 2020/1/14.
 */
@MapperScan(basePackages = "com.xmr.db.mybatis.dao.ds1", sqlSessionTemplateRef = "primarySqlSessionTemplate")
@Configuration
public class MybatisConfig1 {


    //主数据源
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(DataSourceProperties1 dataSourceProperties1) throws SQLException {
//        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        DruidXADataSource druidXADataSource = new DruidXADataSource ();

        druidXADataSource.setUrl(dataSourceProperties1.getUrl());
//        druidXADataSource.setPinGlobalTxToPhysicalConnection(true);
        druidXADataSource.setPassword(dataSourceProperties1.getPassword());
        druidXADataSource.setUsername(dataSourceProperties1.getUsername());
//        druidXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(druidXADataSource);
        xaDataSource.setUniqueResourceName("primaryDataSource");
        xaDataSource.setMinPoolSize(dataSourceProperties1.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dataSourceProperties1.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dataSourceProperties1.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dataSourceProperties1.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dataSourceProperties1.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dataSourceProperties1.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dataSourceProperties1.getMaxIdleTime());
        xaDataSource.setTestQuery(dataSourceProperties1.getTestQuery());
        return xaDataSource;
    }

    //主数据源
    @Primary
    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mybatis/mapper/ds1/*.xml"));
        return sqlSessionFactory.getObject();
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
