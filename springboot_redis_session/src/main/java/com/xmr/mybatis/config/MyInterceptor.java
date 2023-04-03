package com.xmr.mybatis.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xmr on 2020/3/13.
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("========preHandle=========");
        Object account= request.getSession().getAttribute("userAccount");
        if (account==null){
            // response.sendRedirect(request.getContextPath()+"/error");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("请先登录");
            return false;
        }
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("========postHandle=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("========afterCompletion=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        System.out.println(ex);
    }

}
