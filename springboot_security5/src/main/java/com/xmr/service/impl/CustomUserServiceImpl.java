package com.xmr.service.impl;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.SysUser;
import com.xmr.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 12/8/2023
 * @LastEditTime: 12/8/2023 下午 8:27
 * @LastEditors: Administrator
 * @Description:
 */
@Service
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    private ISecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String username) {

        List<SysUser> user = securityDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String password = user.get(0).getPassword();
        return User.withUsername(username).password(password).roles("").build();

    }
}
