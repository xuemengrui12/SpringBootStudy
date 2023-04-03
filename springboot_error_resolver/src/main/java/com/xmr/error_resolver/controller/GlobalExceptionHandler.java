package com.xmr.error_resolver.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xmr on 2019/8/7.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义errors.html，返回错误信息
     * @param request
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public Object errorHandler(HttpServletRequest request,
                               HttpServletResponse response, Exception e)  {
        e.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("my_error");
        return mav;
    }

    /**
     * 返回json数据
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Map<String, Object> defaultExceptionHandler(Exception e) {

        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", e.getMessage());
        return map;
    }
}
