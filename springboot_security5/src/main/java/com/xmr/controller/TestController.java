package com.xmr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController等价于@Controller和@ResponseBody的组合
@RestController
public class TestController {
    @GetMapping("test")
    public String test() {
        return "test";
    }


}