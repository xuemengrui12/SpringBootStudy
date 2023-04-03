package com.xmr.jdbc.dao;


import com.xmr.jdbc.domain.User;

/**
 * Created by xmr on 2019/7/15.
 */
public interface IUserDao {
    void addUser(String name, String sex);

    User getUser(Integer id);

    void updateUser(int id, String name);

    void deleteUserById(Integer id);
}
