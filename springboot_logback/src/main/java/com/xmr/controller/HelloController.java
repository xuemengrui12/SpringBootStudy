package com.xmr.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class HelloController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index() {
        logger.info("hello Sfl4j + logback......");
        log.info("hello Sfl4j + logback......");
        return "Hello World";
    }

    //调用方式为url?id=
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String hello1(@RequestParam("id") Integer id) {
        return "index==" + id;
    }

    //调用方式为：url/{id}
    @RequestMapping(value = "/hello1/{id}", method = RequestMethod.GET)
    public String hello2(@PathVariable("id") Integer id) {
        return "index==" + id;
    }

}