package com.xmr.entity;

import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-03-06 17:45:05
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 870094978174247629L;
    
    private Long id;
    
    private String name;
    
    private Integer count;


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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}