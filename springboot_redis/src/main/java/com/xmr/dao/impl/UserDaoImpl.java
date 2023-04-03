package com.xmr.dao.impl;

import com.xmr.dao.IUserDao;
import com.xmr.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xmr on 2019/7/15.
 */
@Component
public class UserDaoImpl implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addUser(String name, String sex) {
        jdbcTemplate.update("INSERT INTO `user`(`name`,sex) VALUES (?,?)", name, sex);
    }

    @Cacheable(value = "demoInfo",key="'demoInfo_'+#id")
    @Override
    public User getUser(@RequestParam("id") Integer id) {
        List<User> userList = jdbcTemplate.query("select * FROM user WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper(User.class));
        if (userList != null && userList.size() > 0) {
            User user = userList.get(0);
            return user;
        }
        return null;

    }


    @Override
    public void updateUser(int id, String name) {
        jdbcTemplate.update("UPDATE user SET `name` = ? WHERE id = ?", name, id);
    }

    @CacheEvict(value = "demoInfo")
    @Override
    public void deleteUserById(Integer id) {

        jdbcTemplate.update("DELETE FROM `user` WHERE id = ?", id);
    }


}
