package com.xmr.mybatis.model;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2020-03-13 21:18:54
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 444126637794765323L;

    public Account(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    /**
    * 主键id
    */
    private Long id;
    /**
    * 用户名
    */
    private String name;
    /**
    * 密码
    */
    private String pwd;
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}