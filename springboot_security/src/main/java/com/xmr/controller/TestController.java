package com.xmr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2020/2/16.
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "test";
    }

}
