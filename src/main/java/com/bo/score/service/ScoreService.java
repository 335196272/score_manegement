package com.bo.score.service;

import java.util.List;

import com.bo.common.service.BaseService;
import com.bo.score.entity.Classes;
import com.bo.score.entity.Score;
import com.bo.score.vo.ScoreRankChartVo;
import com.bo.score.vo.ScoreRankVo;

/**
 * 成绩业务接口
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
public interface ScoreService extends BaseService<Score> {

	/**
	 * 根据条件查询成绩
	 * @param classesId 班级ID
	 * @param studentNo 学生姓名
	 * @param examId 考试ID
	 * @return
	 * @author DengJinbo, 2017年10月20日.<br>
	 */
	Score findByCondition(long classesId, long studentNo, long examId);

	/**
	 * 查询最近一次考试中，各个班级成绩在各分数段人数（用于统计表）
	 * @param examId 考试ID
	 * @param classesList 班级列表
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	List<ScoreRankVo> listScoreRankVo(long examId, List<Classes> classesList);

	/**
	 * 查询最近一次考试中，各个班级成绩在各分数段人数（用于统计图）
	 * @param examId 考试ID
	 * @param classesList
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	ScoreRankChartVo listScoreRankChartVo(long examId, List<Classes> classesList);

	/**
	 * 根据班级ID和考试ID，查询成绩表，按分数倒序排序
	 * @param classesId 班级ID
	 * @param examId 考试ID
	 * @return
	 * @author DengJinbo, 2017年11月30日.<br>
	 */
	List<Score> listByScoreDesc(long classesId, long examId);

}
