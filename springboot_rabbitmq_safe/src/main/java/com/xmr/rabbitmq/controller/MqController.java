package com.xmr.rabbitmq.controller;

import com.xmr.rabbitmq.pojo.User;
import com.xmr.rabbitmq.service.IMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2020/3/4.
 */
@RestController
public class MqController {

    @Autowired
    private IMqService mqService;
    @RequestMapping("/save")
    public void saveUser(User user){
        mqService.send(user);
    }
}
