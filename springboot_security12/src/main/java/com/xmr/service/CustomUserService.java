package com.xmr.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: Administrator
 * @Date: 31/7/2023
 * @LastEditTime: 31/7/2023 下午 10:45
 * @LastEditors: Administrator
 * @Description:
 */
public interface CustomUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String userName);
}
