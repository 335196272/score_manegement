package com.bo.score.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.ScoreDao;
import com.bo.score.entity.Classes;
import com.bo.score.entity.Score;
import com.bo.score.service.ScoreService;
import com.bo.score.vo.ScoreRankChartVo;
import com.bo.score.vo.ScoreRankVo;

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
	 * @param studentNo 座号
	 * @param examId 考试
	 * @return
	 * @author DengJinbo, 2017年10月20日.<br>
	 */
	@Override
	public Score findByCondition(long classesId, long studentNo, long examId) {
		Score score = null;
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("studentNo", studentNo);
		parameterMap.put("classesId", classesId);
		parameterMap.put("examId", examId);
		List<Score> list = this.list(parameterMap);
		if (list != null && list.size() > 0) {
			score = list.get(0);
		}
		return score;
	}

	/**
	 * 查询最近一次考试中，各个班级成绩在各分数段人数（用于统计表）
	 * @param examId 考试ID
	 * @param classesList 班级列表
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	@Override
	public List<ScoreRankVo> listScoreRankVo(long examId, List<Classes> classesList) {
		int[] rank = {50, 250, 500, 750}; // 查询前50名/前250名/前500名/前750名 的人数
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("examId", examId);
		
		List<ScoreRankVo> scoreRankVoList = new ArrayList<>();
		ScoreRankVo scoreRankVo = null; // 分数排名视图类
		List<Integer> rankList = null; // 各分数段人数集合
		int count = 0; // 各分数段人数
		
		for (Classes classes : classesList) {
			rankList = new ArrayList<Integer>();
			parameterMap.put("classesId", classes.getClassesId());
			for (int r : rank) {
				parameterMap.put("rank", r);
				count = scoreDao.listRankCount(parameterMap);
				rankList.add(count);
			}
			scoreRankVo = new ScoreRankVo();
			scoreRankVo.setClassesName(classes.getName());
			scoreRankVo.setRankList(rankList);
			scoreRankVoList.add(scoreRankVo);
		}
		return scoreRankVoList;
	}

	/**
	 * 查询最近一次考试中，各个班级成绩在各分数段人数（用于统计图）
	 * @param examId
	 * @param classesList
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	@Override
	public ScoreRankChartVo listScoreRankChartVo(long examId, List<Classes> classesList) {
		int[] rank = {50, 250, 500, 750}; // 查询前50名/前250名/前500名/前750名 的人数
		
		List<String> yAxis = new ArrayList<String>();
		List<Integer> data50 = new ArrayList<Integer>();
		List<Integer> data250 = new ArrayList<Integer>();
		List<Integer> data500 = new ArrayList<Integer>();
		List<Integer> data750 = new ArrayList<Integer>();
		int count = 0; // 各分数段人数
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("examId", examId);
		for (Classes classes : classesList) {
			parameterMap.put("classesId", classes.getClassesId());
			for (int i = 0; i < rank.length; i++) {
				parameterMap.put("rank", rank[i]);
				count = scoreDao.listRankCount(parameterMap);
				if (i == 0) {
					data50.add(count);
				}
				if (i == 1) {
					data250.add(count);
				}
				if (i == 2) {
					data500.add(count);
				}
				if (i == 3) {
					data750.add(count);
				}
			}
			yAxis.add('"' + classes.getName() + '"');
		}
		ScoreRankChartVo scoreRankChartVo = new ScoreRankChartVo();
		scoreRankChartVo.setyAxis(yAxis);
		scoreRankChartVo.setData50(data50);
		scoreRankChartVo.setData250(data250);
		scoreRankChartVo.setData500(data500);
		scoreRankChartVo.setData750(data750);
		return scoreRankChartVo;
	}
}