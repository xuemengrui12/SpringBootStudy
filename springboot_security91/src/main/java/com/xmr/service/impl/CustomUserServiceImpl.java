package com.xmr.service.impl;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.SysUser;
import com.xmr.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("customUserService")
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    ISecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String username) {

        List<SysUser> user = securityDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String password = user.get(0).getPassword();
        return User.withUsername(username).password(password).roles("").build();

    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String passwordAfterEncoder = passwordEncoder.encode("admin");
        System.out.println( "---->>>" + passwordAfterEncoder);
        String str=new BCryptPasswordEncoder().encode("guest");
        System.out.println(str);
    }
}
