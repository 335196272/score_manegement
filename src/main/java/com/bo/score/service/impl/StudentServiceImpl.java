package com.bo.score.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.StudentDao;
import com.bo.score.entity.Student;
import com.bo.score.service.StudentService;

/**
 * 学生Service接口实现类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

	@Resource
	private StudentDao studentDao;
	
	@Override
	public BaseDao<Student> getDao() {
		return studentDao;
	}
	
	public static StudentServiceImpl instance() {
		return (StudentServiceImpl) SpringBeanFactoryUtil.getBean("studentService");
	}

}