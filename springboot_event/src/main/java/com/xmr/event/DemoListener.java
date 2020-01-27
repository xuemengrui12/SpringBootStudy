package com.xmr.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * 实现 ApplicationListener接口, 并指定监听的事件类型
 */
@Slf4j
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    /**
     * @param event 使用 onApplicationEvent方法对消息进行接受处理
     */
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        log.info("我(bean-demoListener)接受到了bean-demoPublisher发布的消息:" + msg);
    }

}
