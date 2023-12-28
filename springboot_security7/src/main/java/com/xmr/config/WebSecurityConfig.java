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


    // 定义哪些URL需要被保护、哪些不需要被保护
        httpSecurity.authorizeRequests()
//                .anyRequest().permitAll()
//      定义当需要用户登录时候，转到的登录页面。
                .and().formLogin().loginPage("/login")
//                .failureUrl("/login?error").permitAll() //5
                .and().logout().permitAll() //6
//                .and().authorizeRequests().anyRequest().authenticated() // 任何请求,登录后可以访问

                .and().csrf().disable();
    }


}
