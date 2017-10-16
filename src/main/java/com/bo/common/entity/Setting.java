package com.bo.common.entity;

import java.util.Date;

import com.bo.common.service.impl.UserServiceImpl;

/**
 * 系统配置实体类
 * @author DengJinbo.
 * @Time 2017年9月1日
 */
@SuppressWarnings("serial")
public class Setting extends BaseEntity {
	
	/**
	 * 系统配置ID
	 */
    private long settingId;

    /**
	 * 系统配置名
	 */
    private String name;

    /**
	 * 配置值
	 */
    private String value;

    /**
	 * 配置描述
	 */
    private String description;

    /**
	 * 创建者
	 */
    private long createBy;

    /**
	 * 创建时间
	 */
    private Date createDate;

    /**
	 * 更新者
	 */
    private long updateBy;

    /**
	 * 更新时间
	 */
    private Date updateDate;
    
	public long getSettingId() {
		return settingId;
	}

	public void setSettingId(long settingId) {
		this.settingId = settingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 获取创建者姓名
	 */
	private String createName;
	
	public String getCreateName() {
		User user = UserServiceImpl.instance().find(createBy);
		if (user != null) {
			createName = user.getUserName();
		} else {
			createName = "system";
		}
		return createName;
	}

	/**
	 * 获取更新者姓名
	 */
	private String updateName;

	public String getUpdateName() {
		User user = UserServiceImpl.instance().find(updateBy);
		if (user != null) {
			updateName = user.getUserName();
		} else {
			updateName = "system";
		}
		return updateName;
	}
}