package com.xmr.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xmr.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2020/2/20.
 */
@RestController
public class HelloController {

    @Reference(check = false)//dubbo注解，可以仔细了解下这个注解
    private DemoService demoService;

    @RequestMapping(value = "/hello")
    public String hello() {
        String hello = demoService.sayHello("world");
        System.out.println(demoService.sayHello("BJQ"));
        return hello;
    }
}
