package com.xmr.db.mybatis;

import com.xmr.db.mybatis.config.DataSourceConfig2;
import com.xmr.db.mybatis.config.DataSourceProperties1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {DataSourceProperties1.class, DataSourceConfig2.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
