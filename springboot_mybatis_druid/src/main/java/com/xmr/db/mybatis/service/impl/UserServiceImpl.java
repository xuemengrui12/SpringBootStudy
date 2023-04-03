package com.xmr.db.mybatis.service.impl;

import com.xmr.db.mybatis.dao.UserMapper;
import com.xmr.db.mybatis.domain.User;
import com.xmr.db.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xmr on 2018/3/15.
 */

@Service
//@Scope("single")   //声明scope为prototype
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void addUser(String name, String sex) {
        userMapper.addUser(name, sex);
        int i = 1 / 0;
    }

    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }


    @Override
    public void updateUser(int id, String name) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);

        userMapper.updateUser(id, name);
    }


    @Override
    public void deleteUserById(Integer id) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);

        userMapper.deleteUserById(id);
    }
}
