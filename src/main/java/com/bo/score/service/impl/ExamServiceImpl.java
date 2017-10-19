package com.bo.score.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.impl.BaseServiceImpl;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.score.dao.ExamDao;
import com.bo.score.entity.Exam;
import com.bo.score.service.ExamService;

/**
 * 考试Service接口实现类
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Service("examService")
public class ExamServiceImpl extends BaseServiceImpl<Exam> implements ExamService {

	@Resource
	private ExamDao examDao;
	
	@Override
	public BaseDao<Exam> getDao() {
		return examDao;
	}
	
	public static ExamServiceImpl instance() {
		return (ExamServiceImpl) SpringBeanFactoryUtil.getBean("examService");
	}

	/**
	 * 根据考试名称查询考试
	 * @param name 考试名称
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@Override
	public Exam findByName(String name) {
		return examDao.findByName(name);
	}

	@Override
	public List<Exam> listAllExam() {
		return examDao.listAllExam();
	}

}