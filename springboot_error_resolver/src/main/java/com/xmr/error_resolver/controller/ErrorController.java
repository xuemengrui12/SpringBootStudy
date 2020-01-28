package com.xmr.error_resolver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xmr on 2020/1/11.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * 抛出ArithmeticException
     * @return
     */
    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("msg", "hello");
        int result=5/0;
        return mav;
    }
}
