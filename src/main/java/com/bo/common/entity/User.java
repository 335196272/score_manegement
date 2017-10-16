package com.bo.common.entity;

import java.util.Date;

/**
 * 用户实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class User extends BaseEntity {
	
	/**
	 * 用户ID
	 */
    private long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像链接
     */
    private String picUrl;

    /**
     * 状态：-1=禁用，0=草稿，1=启用
     */
    private int status;

    /**
     * 注册时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 最后登录时间
     */
    private Date lastLoginDate;

    /**
     * 无参构造函数
     */
    public User() { }
    
    /**
     * 带参构造函数
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}