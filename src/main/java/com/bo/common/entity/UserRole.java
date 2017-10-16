package com.bo.common.entity;

/**
 * 用户角色实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class UserRole extends BaseEntity {
	
	/**
	 * 角色权限ID
	 */
    private long userRoleId;

    /**
	 * 用户ID
	 */
    private long userId;

    /**
	 * 角色ID
	 */
    private long roleId;

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}