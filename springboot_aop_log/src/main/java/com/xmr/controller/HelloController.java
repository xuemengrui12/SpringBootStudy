package com.xmr.controller;

import com.xmr.aspect.LoggerManage;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @LoggerManage(description = "测试")
    @RequestMapping("/")
    public String index() {
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