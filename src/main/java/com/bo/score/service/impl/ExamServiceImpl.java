package com.bo.score.service.impl;

import java.util.HashMap;
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

	/**
	 * 查找所有的考试
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	@Override
	public List<Exam> listAllExam() {
		return examDao.listAllExam();
	}

	/**
	 * 查找最新一期的考试
	 * @return
	 * @author DengJinbo, 2017年10月25日.<br>
	 */
	@Override
	public Exam findNewestExam() {
		Exam exam = null;
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("orderField", "exam_time");
		parameterMap.put("orderDirection", "desc");
		List<Exam> list = examDao.list(parameterMap);
		if (list != null && list.size() > 0) {
			exam = list.get(0);
		}
		return exam;
	}

}