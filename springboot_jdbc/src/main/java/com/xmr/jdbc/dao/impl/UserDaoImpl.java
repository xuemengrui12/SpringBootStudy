package com.xmr.jdbc.dao.impl;

import com.xmr.jdbc.dao.IUserDao;
import com.xmr.jdbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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


    @Override
    public User getUser(Integer id) {
        List<User> userList = jdbcTemplate.query("select * FROM first_db.user WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper(User.class));
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


    @Override
    public void deleteUserById(Integer id) {
        jdbcTemplate.update("DELETE FROM `user` WHERE id = ?", id);
    }
}
