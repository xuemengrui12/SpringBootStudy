package com.xmr.mybatis.model;

import java.io.Serializable;


/**
 * @author Administrator
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String passWord;
    private String sex;

    public User() {
        super();
    }

    public User(Long id, String name, String passWord, String sex) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.sex = sex;
    }

    public User(String name, String passWord, String sex) {
        super();
        this.passWord = passWord;
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}