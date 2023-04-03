package com.xmr.db.jdbc.dao;


import com.xmr.db.jdbc.domain.User;

/**
 * Created by xmr on 2019/7/15.
 */
public interface IUserDao {
    void addUser(String name, String sex);

    User getUser(Integer id);

    void updateUser(int id, String name);

    void deleteUserById(Integer id);
}
