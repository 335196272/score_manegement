package com.bo.score.service;

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
	 * 根据考试名称查询考试
	 * @param name 考试名称
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	Exam findByName(String name);

	List<Exam> listAllExam();

}
