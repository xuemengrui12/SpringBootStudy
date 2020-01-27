package com.xmr.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by xmr on 2019/8/20.
 */
public class DemoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    public String msg;

    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
