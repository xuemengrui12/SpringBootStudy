package com.xmr.dao;

import com.xmr.domain.SysUser;

import java.util.List;

public interface ISecurityDao {

	List<SysUser> getUserByUsername(String username);
	List<String> getPermissionsByUsername(String username);
	List<String> getRoleByUsername(String username);
}
