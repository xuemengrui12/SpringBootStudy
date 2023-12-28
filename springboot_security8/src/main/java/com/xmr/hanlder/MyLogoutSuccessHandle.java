package com.xmr.hanlder;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 12/8/2023
 * @LastEditTime: 12/8/2023 下午 10:11
 * @LastEditors: Administrator
 * @Description:
 */
public class MyLogoutSuccessHandle implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("==================>>>> LogoutSuccessHandler Begin");
        System.out.println(authentication.getPrincipal());
        System.out.println("==================>>>> LogoutSuccessHandler End");
    }
}
