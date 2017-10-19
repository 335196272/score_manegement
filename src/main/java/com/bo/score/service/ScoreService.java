package com.bo.score.service;

import com.bo.common.service.BaseService;
import com.bo.score.entity.Score;

/**
 * 成绩业务接口
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
public interface ScoreService extends BaseService<Score> {

	Score findByCondition(long classesId, String studentName, long examId);

}
