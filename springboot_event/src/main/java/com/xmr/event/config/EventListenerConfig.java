package com.xmr.event.config;

/**
 * Created by xmr on 2019/8/20.
 */

import com.xmr.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听配置类
 * 使用@EventListener方式
 *
 * @author xmr
 */
@Configuration
@Slf4j
public class EventListenerConfig {
    @EventListener
    public void handleEvent(Object event) {
        //监听所有事件 可以看看 系统各类时间 发布了哪些事件
        //可根据 instanceof 监听想要监听的事件
        if(event instanceof DemoEvent) {
        log.info("事件：{}", event);
        }
    }

    @EventListener
    public void handleCustomEvent(DemoEvent demoEvent) {
        //监听 CustomEvent事件
        log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", demoEvent.getMsg(), demoEvent.getTimestamp());
    }

    /**
     * 监听msg为xmr的事件
     */
    @Async
    @EventListener(condition = "#demoEvent.msg == 'xmr'")
    public void handleCustomEventByCondition(DemoEvent demoEvent) {
        //监听 CustomEvent事件
        log.info("监听到msg为'xmr'的DemoEvent事件，消息为：{}, 发布时间：{}", demoEvent.getMsg(), demoEvent.getTimestamp());
    }


}
