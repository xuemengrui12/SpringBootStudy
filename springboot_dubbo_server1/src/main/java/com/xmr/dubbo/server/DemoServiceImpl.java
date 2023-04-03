package com.xmr.dubbo.server;


import com.alibaba.dubbo.config.annotation.Service;
import com.xmr.dubbo.api.DemoService;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2020/2/20.
 */
//@Service是dubbo里的注解，作用是暴露服务，不要选择spring的@Service
@Service
@Component
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}