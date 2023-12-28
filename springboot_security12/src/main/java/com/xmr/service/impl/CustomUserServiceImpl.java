package com.xmr.service.impl;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.LoginUser;
import com.xmr.domain.SysUser;
import com.xmr.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 在构建用户信息的时候把用户所属角色和用户所拥有的权限也填充上
 */
@Service("customUserService")
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    private ISecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        List<SysUser> users = securityDao.getUserByUsername(userName);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        String password = users.get(0).getPassword();
        List<String> roles = securityDao.getRoleByUsername(userName);
        List<String> permissions = securityDao.getPermissionsByUsername(userName);

//        String[] roleArr = new String[roles.size()];
//        String[] permissionArr = new String[permissions.size()];
//
//        return User.withUsername(userName).password(password).roles(roles.toArray(roleArr)).
//                authorities(permissions.toArray(permissionArr)).
//                build();

        String[] permissionArr = new String[roles.size() + permissions.size()];
        int permissionArrIndex = 0;
        for (String role : roles) {
            permissionArr[permissionArrIndex] = "ROLE_" + role;
            permissionArrIndex++;
        }
        for (String permission : permissions) {
            permissionArr[permissionArrIndex] = permission;
            permissionArrIndex++;
        }
        List<String> permissionList=new LinkedList<>();
        for (int i = 0; i < permissionArr.length; i++) {
            permissionList.add(permissionArr[i]);
        }
//        return User.withUsername(userName).password(password).authorities(permissionArr).build();
        return new LoginUser(users.get(0), permissionList);

    }


}
