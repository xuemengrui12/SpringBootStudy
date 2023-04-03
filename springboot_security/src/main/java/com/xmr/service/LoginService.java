package com.xmr.service;

import com.xmr.dao.ISecurityDao;
import com.xmr.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xmr on 2020/2/16.
 */
@Service
public class LoginService {
    @Autowired
    private ISecurityDao securityDao;

    public List<SysUser> getUserByUsername(String username) {
        return securityDao.getUserByUsername(username);
    }
    public List<String> getPermissionsByUsername(String username) {
        return securityDao.getPermissionsByUsername(username);
    }

    public List<String> getRoleByUsername(String username) {
        return securityDao.getRoleByUsername(username);
    }
}
