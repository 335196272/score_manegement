package com.bo.score.service;

import java.util.Date;
import java.util.List;

import com.bo.common.service.BaseService;
import com.bo.score.entity.Exam;

/**
 * 考试业务接口
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public interface ExamService extends BaseService<Exam> {

	/**
	 * 根据考试名称和考试时间查询考试
	 * @param name 考试名称
	 * @param examTime 考试时间
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 * @param examTime 
	 */
	Exam findByNameAndExamTime(String name, Date examTime);

	/**
	 * 查找所有的考试
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	List<Exam> listAllExam();

	/**
	 * 查找最新一期的考试
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	Exam findNewestExam();

}
