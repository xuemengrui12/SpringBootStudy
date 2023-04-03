package com.xmr.jdbc.service.impl;

import com.xmr.jdbc.dao.IUserDao;
import com.xmr.jdbc.domain.User;
import com.xmr.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xmr on 2018/3/15.
 */

//@Scope("prototype")   //声明scope为prototype*/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Transactional
    @Override
    public void addUser(String name, String sex) {
        userDao.addUser(name, sex);
        int i = 5 / 0;
    }

    //使用@Transactional注解的 rollbackFor属性,指定特定异常时,数据回滚。
//    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    //使用@Transactional注解的 noRollbackFor属性,指定特定异常时,数据不回滚。
//    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    @Override
    public void updateUser(int id, String name) {
        userDao.updateUser(id, name);
    }


    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }


}
