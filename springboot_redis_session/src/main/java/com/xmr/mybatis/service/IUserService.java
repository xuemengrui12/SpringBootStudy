package com.xmr.mybatis.service;

import com.xmr.mybatis.model.User;

import java.util.List;

/**
 * Created by xmr on 2020/2/25.
 */
public interface IUserService {
    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
