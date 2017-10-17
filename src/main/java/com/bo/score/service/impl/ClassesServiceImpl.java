package com.bo.score.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.ClassesDao;
import com.bo.score.entity.Classes;
import com.bo.score.service.ClassesService;

/**
 * 班级Service接口实现类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Service("classesService")
public class ClassesServiceImpl extends BaseServiceImpl<Classes> implements ClassesService {

	@Resource
	private ClassesDao classesDao;
	
	@Override
	public BaseDao<Classes> getDao() {
		return classesDao;
	}
	
	public static ClassesServiceImpl instance() {
		return (ClassesServiceImpl) SpringBeanFactoryUtil.getBean("classesService");
	}

	/**
	 * 根据班级名称查询班级
	 * @param name 班级名称
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@Override
	public Classes findByName(String name) {
		return classesDao.findByName(name);
	}

}