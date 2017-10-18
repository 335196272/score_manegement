package com.bo.score.dao;

import com.bo.common.dao.BaseDao;
import com.bo.score.entity.Subject;

/**
 * 科目DAO接口
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public interface SubjectDao extends BaseDao<Subject> {

	/**
	 * 根据科目名称查找科目
	 * @param name 科目名称
	 * @return
	 * @author DengJinbo, 2017年10月18日.<br>
	 */
	Subject findByName(String name);

}