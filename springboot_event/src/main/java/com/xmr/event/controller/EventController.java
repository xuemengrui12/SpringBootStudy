package com.xmr.event.controller;

import com.xmr.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2019/8/20.
 */
@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
    /**
     * 注入事件发布类
     */
    @Autowired
    ApplicationEventPublisher eventPublisher;

    /**
     * 参数默认注解式@RequestParam
     * @param message
     * @return
     */
    @GetMapping("/msg")
    public String push(@RequestParam("message") String message) {
        log.info("发布applicationEvent事件:{}", message);
        eventPublisher.publishEvent(new DemoEvent(this, message));
        return "事件发布成功!";
    }

    @GetMapping("/obj")
    public String pushObject(String message) {
        log.info("发布对象事件:{}", message);
        eventPublisher.publishEvent(message);
        return "对象事件发布成功!";
    }
}
