package com.bo.common.entity;

/**
 * 权限实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class Permission extends BaseEntity {
	
	/**
	 * 权限ID
	 */
    private long permissionId;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限标识
     */
    private String permissionSign;

    /**
     * 权限描述（UI界面显示使用）
     */
    private String description;

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionSign() {
		return permissionSign;
	}

	public void setPermissionSign(String permissionSign) {
		this.permissionSign = permissionSign;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}