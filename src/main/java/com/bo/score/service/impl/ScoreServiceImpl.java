package com.bo.score.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.ScoreDao;
import com.bo.score.entity.Score;
import com.bo.score.service.ScoreService;

/**
 * 成绩Service接口实现类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Service("scoreService")
public class ScoreServiceImpl extends BaseServiceImpl<Score> implements ScoreService {

	@Resource
	private ScoreDao scoreDao;
	
	@Override
	public BaseDao<Score> getDao() {
		return scoreDao;
	}
	
	public static ScoreServiceImpl instance() {
		return (ScoreServiceImpl) SpringBeanFactoryUtil.getBean("scoreService");
	}

	/**
	 * 根据条件查询成绩
	 * @param classesId 班级ID
	 * @param studentName 学生姓名
	 * @param examId 考试
	 * @return
	 * @author DengJinbo, 2017年10月20日.<br>
	 */
	@Override
	public Score findByCondition(long classesId, String studentName, long examId) {
		Score score = null;
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("studentName", studentName);
		parameterMap.put("classesId", classesId);
		parameterMap.put("examId", examId);
		List<Score> list = this.list(parameterMap);
		if (list != null && list.size() > 0) {
			score = list.get(0);
		}
		return score;
	}

}