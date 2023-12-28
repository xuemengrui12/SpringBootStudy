package com.xmr.service.impl;

import com.alibaba.fastjson.JSON;
import com.xmr.domain.LoginUser;
import com.xmr.domain.SysUser;
import com.xmr.service.LoginService;
import com.xmr.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: Administrator
 * @Date: 9/8/2023
 * @LastEditTime: 9/8/2023 下午 8:38
 * @LastEditors: Administrator
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    //认证委托
    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
//    private StringRedisTemplate redisTemplate;

    @Override
    public String login(SysUser user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (user.getUsername(), user.getPassword());

        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //将对象转换为 JSON 字符串  存入redis
        String jsonString = JSON.toJSONString(loginUser);
        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisTemplate.opsForValue().set("login:" + userid, jsonString);
//        redisTemplate.opsForValue().set("login:" + userid, loginUser);
        System.out.println(jsonString);
        return jwt;
    }

    public String logout() {
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据userid找到redis对应值进行删除
        redisTemplate.delete("login:" + userid);
        return "注销成功";
    }

}
