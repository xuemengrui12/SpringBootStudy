package com.xmr.db.mybatis.dao;


import com.xmr.db.mybatis.domain.User;

/**
 * Created by xmr on 2019/7/15.
 */
public interface UserMapper {
    void addUser(String name, String sex);

    User getUser(Long id);

    void updateUser(int id, String name);

    void deleteUserById(Integer id);
}
