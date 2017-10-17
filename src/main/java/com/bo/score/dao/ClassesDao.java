package com.bo.score.dao;

import com.bo.common.dao.BaseDao;
import com.bo.score.entity.Classes;

/**
 * 班级DAO接口
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public interface ClassesDao extends BaseDao<Classes> {

	/**
	 * 根据班级名称查询班级
	 * @param name 班级名称
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	Classes findByName(String name);

}