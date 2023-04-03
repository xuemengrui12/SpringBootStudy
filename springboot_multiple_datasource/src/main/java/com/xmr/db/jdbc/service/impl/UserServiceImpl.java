package com.xmr.db.jdbc.service.impl;

import com.xmr.db.jdbc.dao.IUserDao;
import com.xmr.db.jdbc.domain.User;
import com.xmr.db.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xmr on 2018/3/15.
 */

//@Scope("prototype")   //声明scope为prototype
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public void addUser(String name, String sex) {
        userDao.addUser(name,sex);
    }


    @Override
    public User getUser(Integer id) {
       return userDao.getUser(id);
    }


    @Override
    public void updateUser(int id, String name) {
        userDao.updateUser(id,name);
    }


    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }
}
