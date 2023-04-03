package com.xmr.service;


import com.xmr.domain.User;

/**
 * Created by xmr on 2019/7/15.
 */
public interface IUserService {
    void addUser(String name, String sex);

    User getUser(Integer id);

    void updateUser(int id, String name);

    void deleteUserById(Integer id);


    void test();
}
