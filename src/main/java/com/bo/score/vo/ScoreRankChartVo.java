package com.bo.score.vo;

import java.util.List;

/**
 * 各班级、各分数段人数统计图表视图类
 * @author DengJinbo.
 * @Time 2017年10月25日
 */
public class ScoreRankChartVo {
	
    /**
     * Y轴参数列表（班级名称列表）
     */
    private List<String> yAxis;
    
    /**
     * 前50名人数
     */
    private List<Integer> data50;
    
    /**
     * 前250名人数
     */
    private List<Integer> data250;
    
    /**
     * 前500名人数
     */
    private List<Integer> data500;
    
    /**
     * 前750名人数
     */
    private List<Integer> data750;

	public List<String> getyAxis() {
		return yAxis;
	}

	public void setyAxis(List<String> yAxis) {
		this.yAxis = yAxis;
	}

	public List<Integer> getData50() {
		return data50;
	}

	public void setData50(List<Integer> data50) {
		this.data50 = data50;
	}

	public List<Integer> getData250() {
		return data250;
	}

	public void setData250(List<Integer> data250) {
		this.data250 = data250;
	}

	public List<Integer> getData500() {
		return data500;
	}

	public void setData500(List<Integer> data500) {
		this.data500 = data500;
	}

	public List<Integer> getData750() {
		return data750;
	}

	public void setData750(List<Integer> data750) {
		this.data750 = data750;
	}
}