package com.xmr.db.mybatis.service.impl;

import com.xmr.db.mybatis.mapper.ds1.UserMapper;
import com.xmr.db.mybatis.domain.User;
import com.xmr.db.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xmr on 2018/3/15.
 */

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(String name, String sex) {
        userMapper.addUser(name, sex);
    }


    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }


    @Override
    public void updateUser(int id, String name) {
        userMapper.updateUser(id, name);
    }


    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
