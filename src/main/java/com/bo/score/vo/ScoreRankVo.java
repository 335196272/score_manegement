package com.bo.score.vo;

import java.util.List;

/**
 * 各班级、各分数段人数视图类
 * @author DengJinbo.
 * @Time 2017年10月25日
 */
public class ScoreRankVo {
	
    /**
     * 班级名称
     */
    private String classesName;
    
    /**
     * 分数段人数统计（前50名/前250名/前500名/前750名 的人数）
     */
    private List<Integer> rankList;
    
	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public List<Integer> getRankList() {
		return rankList;
	}

	public void setRankList(List<Integer> rankList) {
		this.rankList = rankList;
	}
}