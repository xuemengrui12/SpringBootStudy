package com.xmr.domain;

import java.io.Serializable;

/**
 * Created by xmr on 2018/3/15.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8791412805007288891L;
    int id;
    String name;
    String sex;

    public User( String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", sex='" + sex + '\'' +
                '}';
    }
}
