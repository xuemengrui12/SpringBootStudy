package com.xmr.config;

import com.xmr.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Administrator
 * @Date: 31/7/2023
 * @LastEditTime: 31/7/2023 下午 10:12
 * @LastEditors: Administrator
 * @Description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //使用数据库方式存储
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.
                //关闭csrf
                csrf().disable().
                authorizeRequests().antMatchers("/guest/**").permitAll().
                and().authorizeRequests().antMatchers("/admin/**").hasRole("admin").
                and().authorizeRequests().antMatchers("/authenticated/**").authenticated().
                and().authorizeRequests().antMatchers("/permission1/**").hasAuthority("permission1").
                and().authorizeRequests().antMatchers("/permission2/**").hasAuthority("permission2").
                and().authorizeRequests().antMatchers("/permission3/**").hasAuthority("permission3").
                and().authorizeRequests().antMatchers("/permission4/**").hasAuthority("permission4").
                and().formLogin().
                and().authorizeRequests().anyRequest().permitAll();

    }


}
