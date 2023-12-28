package com.xmr.config;

import com.xmr.hanlder.MyAuthenticationSuccessHandler;
import com.xmr.hanlder.MyLogoutHandle;
import com.xmr.hanlder.MyLogoutSuccessHandle;
import com.xmr.service.impl.CustomUserServiceImpl;
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
    private CustomUserServiceImpl customUserService;

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
        httpSecurity.formLogin().loginPage("/login").successHandler(new MyAuthenticationSuccessHandler()).//自定义登录成功处理类
                and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess").
                invalidateHttpSession(true).deleteCookies("cookiename").
                addLogoutHandler(new MyLogoutHandle()).logoutSuccessHandler(new MyLogoutSuccessHandle()).
                and().authorizeRequests().anyRequest().permitAll().
                and().csrf().disable();
    }


}
