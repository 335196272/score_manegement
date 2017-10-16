package com.bo.common.entity;

/**
 * 角色权限实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class RolePermission extends BaseEntity {
	
	/**
	 * 角色权限ID
	 */
    private long rolePermissionId;

    /**
	 * 角色ID
	 */
    private long roleId;

    /**
	 * 权限ID
	 */
    private long permissionId;

	public long getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(long rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
}