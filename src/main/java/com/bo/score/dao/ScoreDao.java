package com.bo.score.dao;

import java.util.HashMap;
import java.util.List;

import com.bo.common.dao.BaseDao;
import com.bo.score.entity.Score;

/**
 * 成绩DAO接口
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
public interface ScoreDao extends BaseDao<Score> {

	/**
	 * 查询最近一次考试中，各个班级成绩在各分数段人数
	 * @param parameterMap
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	Integer listRankCount(HashMap<String, Object> parameterMap);

	/**
	 * 根据班级ID和考试ID，查询成绩表，按分数倒序排序
	 * @param parameterMap
	 * @return
	 * @author DengJinbo, 2017年11月30日.<br>
	 */
	List<Score> listByScoreDesc(HashMap<String, Object> parameterMap);
	
	int countByScore(HashMap<String, Object> parameterMap);
}