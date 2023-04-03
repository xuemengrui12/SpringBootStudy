package com.xmr.security;

import com.xmr.dao.SysUserRepository;
import com.xmr.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserService implements UserDetailsService { //1
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) { //2
		
		SysUser user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		String password = user.getPassword();
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passwordAfterEncoder = passwordEncoder.encode(password);
		return User.withUsername(username).password(passwordAfterEncoder).roles("").build();

//		return user; //3
	}

}
