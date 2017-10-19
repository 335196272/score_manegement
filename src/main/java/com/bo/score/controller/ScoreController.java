package com.bo.score.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bo.common.service.LogService;
import com.bo.common.util.Pager;
import com.bo.common.util.T;
import com.bo.score.entity.Classes;
import com.bo.score.entity.Exam;
import com.bo.score.entity.Score;
import com.bo.score.service.ClassesService;
import com.bo.score.service.ExamService;
import com.bo.score.service.ScoreService;

/**
 * 成绩控制器
 * @author DengJinbo.
 * @Time 2017年10月19日
 */
@Controller
@RequestMapping("/admin/score")
public class ScoreController {

	@Resource
	private ScoreService scoreService;
	
	@Resource
	private LogService logService;
	
	@Resource
	private ClassesService classesService;
	
	@Resource
	private ExamService examService;
	
	/**
	 * 列表
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月19日.<br>
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest req) {
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 15);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String studentName = T.stringValue(req.getParameter("studentName"), null);
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		long examId = T.longValue(req.getParameter("examId"), 0);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "exam_id" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
		parameterMap.put("studentName", studentName);
		parameterMap.put("classesId", classesId);
		parameterMap.put("examId", examId);
		Pager<Score> pager = scoreService.pager(parameterMap);
		
		List<Classes> classesList = classesService.listAllClasses();
		List<Exam> examList = examService.listAllExam();
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
		req.setAttribute("classesList", classesList);
		req.setAttribute("examList", examList);
    	return "admin/score/list"; 
    }
	
}