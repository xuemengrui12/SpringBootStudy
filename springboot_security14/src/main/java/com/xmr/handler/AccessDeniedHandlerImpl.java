package com.xmr.handler;

/**
 * @Author: Administrator
 * @Date: 17/8/2023
 * @LastEditTime: 17/8/2023 下午 9:08
 * @LastEditors: Administrator
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.xmr.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc : 授权的异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String,Object> result=new HashMap<>();
        result.put("msg", new String("您的权限不足".toString().getBytes("UTF-8")));
//        result.put("msg", "Your permissions are insufficient");
        result.put("code", HttpStatus.FORBIDDEN.value());
        String json = JSON.toJSONString(result);
        //处理移除
        WebUtils.renderString(response,json);
    }
}

