package com.bo.common.dao;

import java.util.List;

import com.bo.common.entity.Role;

/**
 * 角色DAO接口
 * @author DengJinbo.
 * @Time 2017年9月4日
 */
public interface RoleDao extends BaseDao<Role> {

	/**
	 * 根据用户ID查询用户角色列表
	 * @param userId
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	List<Role> listRolesByUserId(Long userId);

}