package com.xmr.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by xmr on 2018/3/14.
 * 通过@Entity 表明是一个映射的实体类， @Id表明id， @GeneratedValue 字段自动生成
 */
@Entity
@NamedQuery(name = "Girl.findByName", query = "select g from Girl g  where g.name=?1")
public class Girl {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message="姓名不能为空！")
    private String name;
    @Min(value = 18, message = "年龄必须大于18岁！")
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
