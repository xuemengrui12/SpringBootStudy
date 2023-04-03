package com.xmr.db.mybatis.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by xmr on 2020/1/14.
 */
@MapperScan(basePackages = "com.xmr.db.mybatis.dao.ds2", sqlSessionTemplateRef = "secondarySqlSessionTemplate")
@Configuration
public class MybatisConfig2 {


    @Bean("secondaryDataSource")
    public DataSource secondaryDataSource(DataSourceConfig2 dataSourceConfig2) throws Exception {
        DruidXADataSource datasource = new DruidXADataSource ();
        BeanUtils.copyProperties(dataSourceConfig2,datasource);
//        mysqlXaDataSource.setUrl(dataSourceConfig2.getUrl());
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
//        mysqlXaDataSource.setPassword(dataSourceConfig2.getPassword());
//        mysqlXaDataSource.setUser(dataSourceConfig2.getUsername());
//        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName("secondaryDataSource");

//        xaDataSource.setMinPoolSize(dataSourceConfig2.getMinPoolSize());
//        xaDataSource.setMaxPoolSize(dataSourceConfig2.getMaxPoolSize());
//        xaDataSource.setMaxLifetime(dataSourceConfig2.getMaxLifetime());
//        xaDataSource.setBorrowConnectionTimeout(dataSourceConfig2.getBorrowConnectionTimeout());
//        xaDataSource.setLoginTimeout(dataSourceConfig2.getLoginTimeout());
//        xaDataSource.setMaintenanceInterval(dataSourceConfig2.getMaintenanceInterval());
//        xaDataSource.setMaxIdleTime(dataSourceConfig2.getMaxIdleTime());
//        xaDataSource.setTestQuery(dataSourceConfig2.getTestQuery());
        return xaDataSource;

    }
    //主数据源
    @Primary
    @Bean("secondarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath*:mybatis/mapper/ds2/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
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
