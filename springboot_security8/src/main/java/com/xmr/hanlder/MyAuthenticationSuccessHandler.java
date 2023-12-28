package com.xmr.hanlder;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 20/8/2023
 * @LastEditTime: 20/8/2023 下午 4:08
 * @LastEditors: Administrator
 * @Description:
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> result=new HashMap<>();
        result.put("msg", new String("登录成功".toString().getBytes("UTF-8")));
//        result.put("msg", "Your permissions are insufficient");
        result.put("code", HttpStatus.OK.value());
        String json = JSON.toJSONString(result);

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(json);
        response.getWriter().flush();
    }
}
