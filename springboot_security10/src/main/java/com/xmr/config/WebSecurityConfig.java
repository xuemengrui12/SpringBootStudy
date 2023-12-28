package com.xmr.config;

import com.xmr.filter.JwtAuthenticationTokenFilter;
import com.xmr.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    @Qualifier("customUserService")
    private CustomUserService customUserService;
    //把token校验过滤器注入配置类
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //通过代码方式添加用户
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("admin"))
//                .roles("");
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("guest")
//                .password(new BCryptPasswordEncoder().encode("guest"))
//                .roles("");
        //账号密码明文存储
//        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
        //使用数据库方式存储
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        //任何请求都可以访问
//        httpSecurity.authorizeRequests().antMatchers("/**").permitAll();

        // 定义哪些URL需要被保护、哪些不需要被保护
//        httpSecurity.authorizeRequests()
//                .anyRequest().authenticated() // 任何请求,登录后可以访问
//                //  定义当需要用户登录时候，转到的登录页面。
//                .and().formLogin().loginPage("/login")
//                .failureUrl("/login?error").permitAll() //5
//                .and().logout().permitAll() //6
//                .and().csrf().disable();

//        httpSecurity.
//                authorizeRequests().antMatchers("/guest/**").permitAll().
//                and().authorizeRequests().antMatchers("/admin/**").hasRole("admin").
//                and().authorizeRequests().antMatchers("/authenticated/**").authenticated().
//                and().authorizeRequests().antMatchers("/permission1/**").hasAuthority("permission1").
//                and().authorizeRequests().antMatchers("/permission2/**").hasAuthority("permission2").
//                and().authorizeRequests().antMatchers("/permission3/**").hasAuthority("permission3").
//                and().authorizeRequests().antMatchers("/permission4/**").hasAuthority("permission4").
//                and().authorizeRequests().anyRequest().permitAll();

        httpSecurity
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/user/login").permitAll()// 对于登录接口 允许访问
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //把token校验过滤器添加到过滤器链中
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //把AuthenticationManager注入容器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
