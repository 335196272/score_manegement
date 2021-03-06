package com.bo.score.dao;

import java.util.HashMap;
import java.util.List;

import com.bo.common.dao.BaseDao;
import com.bo.score.entity.Exam;

/**
 * 考试DAO接口
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public interface ExamDao extends BaseDao<Exam> {

	/**
	 * 根据考试名称和考试时间查询考试
	 * @param parameterMap
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	Exam findByNameAndExamTime(HashMap<String, Object> parameterMap);

	/**
	 * 查找所有的考试
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	List<Exam> listAllExam();

}