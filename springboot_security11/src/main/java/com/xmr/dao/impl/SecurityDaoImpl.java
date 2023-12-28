package com.xmr.dao.impl;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xmr on 2020/2/16.
 */
@Repository
public class SecurityDaoImpl implements ISecurityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SysUser> getUserByUsername(String username) {
        String sql = "select id, username, password from sys_user where username = ?";
        return jdbcTemplate.query(sql, new String[]{username}, new BeanPropertyRowMapper<>(SysUser.class));

    }

    @Override
    public List<String> getPermissionsByUsername(String username) {
        String sql =
                "select d.permission from sys_user a " +
                        "       join user_r_role b on a.id = b.user_id\n" +
                        "       join role_r_permission c on b.role_id = c.role_id\n" +
                        "       join sys_permission d on c.permission_id = d.id\n" +
                        "where a.username = ?\n" +
                        "union\n" +
                        "select c.permission\n" +
                        "from sys_user a\n" +
                        "       join user_r_permission b on a.id = b.user_id\n" +
                        "       join sys_permission c on b.permission_id = c.id\n" +
                        "where a.username = ?";
        return jdbcTemplate.queryForList(sql, new String[]{username, username}, String.class);
    }

    @Override
    public List<String> getRoleByUsername(String username) {
        String sql =
                "select c.name\n" +
                        "from sys_user a\n" +
                        "       join user_r_role b on a.id = b.user_id\n" +
                        "       join sys_role c on b.role_id = c.id\n" +
                        "where a.username = ?";
        return jdbcTemplate.queryForList(sql, new String[]{username}, String.class);
    }


}
