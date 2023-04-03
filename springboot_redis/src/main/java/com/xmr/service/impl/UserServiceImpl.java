package com.xmr.service.impl;

import com.xmr.dao.IUserDao;
import com.xmr.domain.User;
import com.xmr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xmr on 2018/3/15.
 */

//@Scope("prototype")   //声明scope为prototype*/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

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
        System.err.println("=========从数据库中进行获取的....id=" + id);

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
        System.out.println("================从缓存中删除.");
        userDao.deleteUserById(id);
    }

    @Override
    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1=" + Math.random());
        System.out.println(valueOperations.get("mykey4"));
    }

}
