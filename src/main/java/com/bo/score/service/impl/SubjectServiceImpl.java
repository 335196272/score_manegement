package com.bo.score.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.SubjectDao;
import com.bo.score.entity.Subject;
import com.bo.score.service.SubjectService;

/**
 * 科目Service接口实现类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Service("subjectService")
public class SubjectServiceImpl extends BaseServiceImpl<Subject> implements SubjectService {

	@Resource
	private SubjectDao subjectDao;
	
	@Override
	public BaseDao<Subject> getDao() {
		return subjectDao;
	}
	
	public static SubjectServiceImpl instance() {
		return (SubjectServiceImpl) SpringBeanFactoryUtil.getBean("subjectService");
	}

	@Override
	public Subject findByName(String name) {
		return subjectDao.findByName(name);
	}

}