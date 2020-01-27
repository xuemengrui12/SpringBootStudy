package com.xmr.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2019/8/20.
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext;//注入 AppllcationContext用来发布事件

    /**
     * @param msg
     * 使用 AppllicationContext的 publishEvent方法来发布
     */
    public void publish(String msg){
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }

}
