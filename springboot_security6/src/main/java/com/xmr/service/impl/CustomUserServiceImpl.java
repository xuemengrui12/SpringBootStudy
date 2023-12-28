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
    ISecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String userName) {

        List<SysUser> users = securityDao.getUserByUsername(userName);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        String password = users.get(0).getPassword();
        List<String> roles = securityDao.getRoleByUsername(userName);
        List<String> permissions = securityDao.getPermissionsByUsername(userName);
        String[] roleArr = new String[roles.size()];
        String[] permissionArr = new String[permissions.size()];
        //roles(roles.toArray(roleArr))起不到任何作用，直接被后面的authorities(permissions.toArray(permissionArr))覆盖掉了
        return User.withUsername(userName).password(password).roles(roles.toArray(roleArr)).
                authorities(permissions.toArray(permissionArr)).
                build();
    }
}
