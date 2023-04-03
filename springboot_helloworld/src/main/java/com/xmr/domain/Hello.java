package com.xmr.domain;

/**
 * Created by xmr on 2018/4/2.
 */
public class Hello {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello() {
        return "Hello" + msg;
    }
}
