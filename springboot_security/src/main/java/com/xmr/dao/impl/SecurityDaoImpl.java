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

    public List<String> getPermissionsByUsername(String username) {
        String sql =
                "select permission.permission from user join user_r_role on user.id = user_r_permission.userid\n" +
                        "       join role_r_permission c on user_r_role.roleid = role_r_permission.roleid\n" +
                        "       join permission  on role_r_permission.permissionid = permission.id\n" +
                        "where user.username = ?\n" +
                        "union\n" +
                        "select role_r_permission.permission\n" +
                        "from user a\n" +
                        "       join user_r_permission b on user.id = user_r_role.userid\n" +
                        "       join permission c on user_r_role.permissionid = role_r_permission.id\n" +
                        "where user.username = ?";
        return jdbcTemplate.queryForList(sql, new String[]{username, username}, String.class);
    }

    public List<String> getRoleByUsername(String username) {
        String sql =
                "select c.role\n" +
                        "from user a\n" +
                        "       join user_r_role b on user.id = user_r_role.userid\n" +
                        "       join role c on user_r_role.roleid = c.id\n" +
                        "where user.username = ?";
        return jdbcTemplate.queryForList(sql, new String[]{username}, String.class);
    }


}
