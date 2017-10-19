package com.bo.score.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.bo.common.entity.User;
import com.bo.common.service.impl.UserServiceImpl;
import com.bo.score.service.impl.ClassesServiceImpl;
import com.bo.score.service.impl.ExamServiceImpl;

/**
 * 成绩实体类
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
public class Score {
	
	/**
	 * 成绩ID
	 */
    private long scoreId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 班级ID
     */
    private long classesId;

    /**
     * 考试ID
     */
    private long examId;

    /**
     * 分数
     */
    private BigDecimal chinese;

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

	public long getScoreId() {
		return scoreId;
	}

	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getClassesId() {
		return classesId;
	}

	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public BigDecimal getChinese() {
		return chinese;
	}

	public void setChinese(BigDecimal chinese) {
		this.chinese = chinese;
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
	 * 获取班级名称
	 */
	private String classesName;
	
	public String getClassesName() {
		Classes classes = ClassesServiceImpl.instance().find(classesId);
		if (classes != null) {
			classesName = classes.getName();
		} else {
			classesName = "undefine!";
		}
		return classesName;
	}
	
	/**
	 * 获取考试名称
	 */
	private String examName;

	public String getExamName() {
		Exam exam = ExamServiceImpl.instance().find(examId);
		if (exam != null) {
			examName = exam.getName();
		} else {
			examName = "undefine!";
		}
		return examName;
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