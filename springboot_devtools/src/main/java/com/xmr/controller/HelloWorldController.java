package com.xmr.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    //调用方式为url?id=
    @RequestMapping(value = {"/hello", "/h1"}, method = RequestMethod.GET)
    public String hello1(@RequestParam("id") Integer id) {
        return "index==" + id;
    }

    //调用方式为：url/{id}
    @RequestMapping(value = "/hello1/{id}", method = RequestMethod.GET)
    public String hello2(@PathVariable("id") Integer id) {
        return "index==" + id;
    }

}