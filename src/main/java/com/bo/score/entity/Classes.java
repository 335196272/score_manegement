package com.bo.score.entity;

import java.util.Date;

import com.bo.common.entity.BaseEntity;
import com.bo.common.entity.User;
import com.bo.common.service.impl.UserServiceImpl;

/**
 * 班级实体类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@SuppressWarnings("serial")
public class Classes extends BaseEntity {
	
	/**
	 * 班级ID
	 */
    private long classesId;

    /**
     * 班级名称（例如：一年级1班）
     */
    private String name;

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

	public long getClassesId() {
		return classesId;
	}

	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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