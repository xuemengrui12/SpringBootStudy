package com.xmr.error_resolver.controller;

import org.apache.naming.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理
 * Created by xmr on 2020/1/11.
 */
//@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        String ex=null;
        if( e instanceof Exception){
            ex=e.getMessage();
        }else{
            ex="未知错误";
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", httpServletRequest.getRequestURL());
        mv.setViewName("my_error");
        return mv;
    }
}
