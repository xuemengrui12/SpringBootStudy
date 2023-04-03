package com.xmr.error_resolver.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在controller类内部定义异常处理方法
 * Created by xmr on 2020/1/11.
 */
@RestController
@RequestMapping("/handler")
public class HandlerController {
    @RequestMapping("/hello")
    public void test() {
        throw new NullPointerException("出错了！");
    }

    @ExceptionHandler({NullPointerException.class})
    public String exception(NullPointerException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return "null pointer exception";
    }
}
