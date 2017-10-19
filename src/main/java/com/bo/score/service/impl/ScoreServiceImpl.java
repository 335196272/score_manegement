package com.bo.score.service.impl;

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

	@Override
	public Score findByCondition(long classesId, String studentName, long examId) {
		// TODO Auto-generated method stub
		return null;
	}

}