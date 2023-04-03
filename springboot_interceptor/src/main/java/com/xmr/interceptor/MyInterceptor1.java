package com.xmr.interceptor;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xmr on 2019/8/6.
 */
public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        System.out.println("========preHandle1=========");
//        WebApplicationContextUtils
        request.getServletContext();
        String sessionId=request.getRequestedSessionId();
        String url=request.getRequestURL().toString();
        String uri=request.getRequestURI();
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
        System.out.println("========postHandle1=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("========afterCompletion1=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        System.out.println(ex);
    }
}