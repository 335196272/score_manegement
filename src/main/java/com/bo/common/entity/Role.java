package com.bo.common.entity;

/**
 * 角色实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class Role extends BaseEntity {
	
	/**
	 * 角色ID
	 */
    private long roleId;

    /**
	 * 角色名
	 */
    private String roleName;

    /**
	 * 角色标识（程序中判断使用,如"admin"）
	 */
    private String roleSign;

    /**
	 * 角色描述（UI界面显示使用）
	 */
    private String description;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleSign() {
		return roleSign;
	}

	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}