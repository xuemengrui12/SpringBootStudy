package com.xmr.security;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

public class CustomUserService implements UserDetailsService {
	@Autowired
	ISecurityDao securityDao;
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		SysUser user = (SysUser) securityDao.getUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		String password = user.getPassword();
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passwordAfterEncoder = passwordEncoder.encode(password);
		System.out.println(username + "---->>>" + passwordAfterEncoder);
		return User.withUsername(username).password(passwordAfterEncoder).roles("").build();

	}

}
