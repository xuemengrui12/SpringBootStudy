package com.xmr.mybatis.dao;

import com.xmr.mybatis.model.User;

import java.util.List;


public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}