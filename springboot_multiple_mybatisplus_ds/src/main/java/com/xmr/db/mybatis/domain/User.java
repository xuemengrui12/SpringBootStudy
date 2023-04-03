package com.xmr.db.mybatis.domain;

/**
 * Created by xmr on 2018/3/15.
 */
public class User {
    int id;
    String name;
    String sex;

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
}
