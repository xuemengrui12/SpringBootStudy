package com.xmr.handler;

/**
 * @Author: Administrator
 * @Date: 17/8/2023
 * @LastEditTime: 17/8/2023 下午 9:36
 * @LastEditors: Administrator
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.xmr.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理认证异常
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String,Object> result=new HashMap<>();
        result.put("msg", new String("用户名认证失败请重新登录".toString().getBytes("UTF-8")));
//        result.put("msg", "Login Failed. Please login to the system again!");
        result.put("code", HttpStatus.UNAUTHORIZED.value());
        String json = JSON.toJSONString(result);
        //处理移除
        WebUtils.renderString(response,json);

    }
}