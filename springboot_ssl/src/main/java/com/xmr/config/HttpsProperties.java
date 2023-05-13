package com.xmr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @Date: 9/4/2023
 * @LastEditTime: 9/4/2023 下午 9:19
 * @LastEditors: Administrator
 * @Description:
 */
@ConfigurationProperties(prefix = "https")
public class HttpsProperties {
    private  Integer port;
    private String keyStoreFile;
    private String keyStorePsd;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getKeyStoreFile() {
        return keyStoreFile;
    }

    public void setKeyStoreFile(String keyStoreFile) {
        this.keyStoreFile = keyStoreFile;
    }

    public String getKeyStorePsd() {
        return keyStorePsd;
    }

    public void setKeyStorePsd(String keyStorePsd) {
        this.keyStorePsd = keyStorePsd;
    }
}
