package com.bo.score.entity;

import java.util.Date;

/**
 * 学生实体类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public class Student {
	
	/**
     * 学生ID
     */
    private long studentId;

    /**
     * 学号
     */
    private long studentNumber;

    /**
     * 性别：0=女，1=男
     */
    private String name;

    /**
     * 
     */
    private int sex;

    /**
     * 入学时间（用于区分不同届的学生，只保存年份）
     */
    private Date entranceTime;

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

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(long studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
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
}