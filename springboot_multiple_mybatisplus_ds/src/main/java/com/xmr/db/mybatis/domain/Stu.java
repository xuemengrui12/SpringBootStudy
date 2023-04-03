package com.xmr.db.mybatis.domain;

/**
 * Created by xmr on 2018/3/15.
 */
public class Stu {
    private long id;
    private String name;
    private String sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
