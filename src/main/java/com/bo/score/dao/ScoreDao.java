package com.bo.score.dao;

import java.util.HashMap;

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
}