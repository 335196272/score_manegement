package com.bo.score.service;

import com.bo.common.service.BaseService;
import com.bo.score.entity.Score;

/**
 * 成绩业务接口
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
public interface ScoreService extends BaseService<Score> {

	/**
	 * 根据条件查询成绩
	 * @param classesId 班级ID
	 * @param studentName 学生姓名
	 * @param examId 考试
	 * @return
	 * @author DengJinbo, 2017年10月20日.<br>
	 */
	Score findByCondition(long classesId, String studentName, long examId);

}
