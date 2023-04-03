package com.xmr.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xmr on 2018/4/2.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private static final String MSG="world";
    private String msg=MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
