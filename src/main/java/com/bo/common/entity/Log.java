package com.bo.common.entity;

import java.util.Date;

/**
 * 系统日志实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class Log extends BaseEntity {
	
	/**
	 * 日志ID
	 */
    private long logId;

    /**
	 * 用户名
	 */
    private String name;

    /**
	 * 操作：创建，修改，删除，更新状态等
	 */
    private String action;

    /**
	 * 日志详情
	 */
    private String detail;
    
    /**
	 * IP地址
	 */
    private String ip;

    /**
	 * 创建时间
	 */
    private Date createDate;

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}