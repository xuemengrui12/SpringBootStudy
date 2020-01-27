package com.xmr.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by xmr on 2020/1/27.
 */
@Slf4j
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        // TODO Auto-generated method stub
        //由于 log相关还未加载 使用了也输出不了的
        log.info("ApplicationStartingEvent事件发布:{}", event);
        System.out.println("ApplicationStartingEvent事件发布:" + event.getTimestamp());
    }
}
