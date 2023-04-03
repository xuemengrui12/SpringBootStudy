package com.xmr.mybatis.service.impl;

import com.xmr.mybatis.dao.UserMapper;
import com.xmr.mybatis.model.User;
import com.xmr.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xmr on 2020/2/25.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }
}
