package com.bo.score.entity;

import java.util.Date;

import com.bo.common.entity.User;
import com.bo.common.service.impl.UserServiceImpl;

/**
 * 考试实体类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public class Exam {
	
	/**
	 * 考试ID
	 */
    private long examId;

    /**
     * 考试名称（例如：2017年10月份月考）
     */
    private String name;

    /**
     * 考试时间
     */
    private Date examTime;
    
    /**
     * 满分分数
     */
    private int fullMarks;

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

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public int getFullMarks() {
		return fullMarks;
	}

	public void setFullMarks(int fullMarks) {
		this.fullMarks = fullMarks;
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