package com.bo.score.entity;

import java.util.Date;

/**
 * 科目实体类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public class Subject {
	
	/**
	 * 科目ID
	 */
    private long subjectId;

    /**
     * 科目名称
     */
    private String name;

    /**
     * 排序
     */
    private int seq;

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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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