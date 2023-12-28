package com.xmr.hanlder;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Administrator
 * @Date: 12/8/2023
 * @LastEditTime: 12/8/2023 下午 10:10
 * @LastEditors: Administrator
 * @Description:
 */
public class MyLogoutHandle implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        System.out.println("==================>>>> LogoutHandler Begin");
        System.out.println(authentication.getPrincipal());
        System.out.println("==================>>>> LogoutHandler End");
    }
}
