package com.bo.common.dao;

import java.util.List;

import com.bo.common.entity.Permission;

/**
 * 权限DAO接口
 * @author DengJinbo.
 * @Time 2017年9月4日
 */
public interface PermissionDao extends BaseDao<Permission> {

	/**
	 * 根据角色ID查询角色拥有的权限列表
	 * @param roleId
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	List<Permission> listPermissionsByRoleId(Long roleId);
	
}