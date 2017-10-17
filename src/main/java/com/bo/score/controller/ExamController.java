package com.bo.score.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bo.common.entity.User;
import com.bo.common.enums.LogTypeEnum;
import com.bo.common.service.LogService;
import com.bo.common.util.IpUtils;
import com.bo.common.util.Pager;
import com.bo.common.util.T;
import com.bo.score.entity.Exam;
import com.bo.score.service.ExamService;

/**
 * 考试控制器
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Controller
@RequestMapping("/admin/exam")
public class ExamController {

	@Resource
	private ExamService examService;
	
	@Resource
	private LogService logService;
	
	/**
	 * 列表
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest req) {
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 15);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String name = T.stringValue(req.getParameter("name"), null);
		String ge_examTime = T.stringValue(req.getParameter("ge_examTime"), null);
		String le_examTime = T.stringValue(req.getParameter("le_examTime"), null);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "exam_time" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
		parameterMap.put("name", name);
		parameterMap.put("ge_examTime", ge_examTime);
		parameterMap.put("le_examTime", le_examTime);
		Pager<Exam> pager = examService.pager(parameterMap);
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
    	return "admin/exam/list"; 
    }
	
	/**
	 * 跳转新增页面
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@RequestMapping(value = "/toAdd.html", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest req) {
    	return "admin/exam/add"; 
    }
	
	/**
	 * 新增
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String name = T.stringValue(req.getParameter("name"), null);
		Date examTime = T.dateValue(req.getParameter("examTime"), "yyyy-MM-dd", T.getNow());
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		Exam findExam = examService.findByName(name);
		if (findExam != null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "考试已经存在，请不要重复创建！");
			return resultMap;
		}
		Exam entity = new Exam();
		entity.setName(name);
		entity.setExamTime(examTime);
		entity.setCreateBy(currentUser.getUserId());
		entity.setCreateDate(T.getNow());
		entity.setUpdateBy(currentUser.getUserId());
		entity.setUpdateDate(T.getNow());
		examService.create(entity);
		
		resultMap.put("status", 200);
		resultMap.put("msg", "创建成功！");
		return resultMap;
	}
	
	/**
	 * 跳转修改页面
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@RequestMapping(value = "/toModify.html", method = RequestMethod.GET)
    public String toModify(HttpServletRequest req) {
		long examId = T.longValue(req.getParameter("examId"), 0);
		Exam entity = examService.find(examId);
		req.setAttribute("entity", entity);
    	return "admin/exam/modify"; 
    }
	
	/**
	 * 修改
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/modify.html", method = RequestMethod.POST)
	public Map<String, Object> modify(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		long examId = T.longValue(req.getParameter("examId"), 0);
		String name = T.stringValue(req.getParameter("name"), null);
		Date examTime = T.dateValue(req.getParameter("examTime"), "yyyy-MM-dd", T.getNow());
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		
		Exam entity = examService.find(examId);
		if (entity == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "修改失败，考试不存在！");
			return resultMap;
		}
		Exam findExam = examService.findByName(name);
		if (findExam != null && findExam.getExamId() != examId) {
			resultMap.put("status", 300);
			resultMap.put("msg", "考试已经存在，不允许修改！");
			return resultMap;
		}
		entity.setName(name);
		entity.setExamTime(examTime);
		entity.setUpdateBy(currentUser.getUserId());
		entity.setUpdateDate(T.getNow());
		examService.update(entity);
		
		resultMap.put("status", 200);
		resultMap.put("msg", "修改成功！");
		return resultMap;
	}
	
	/**
	 * 删除
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		long examId = T.longValue(req.getParameter("examId"), 0);
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		
		Exam entity = examService.find(examId);
		if (entity == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "删除失败，考试不存在！");
			return resultMap;
		}
		try {
			logService.log(currentUser.getUserName(), LogTypeEnum.DELETE.getDesc(), 
					"删除考试：“" + entity.getName() + "”！", IpUtils.getIp(req));
			examService.delete(examId);
		} catch (Exception e) {
			resultMap.put("status", 300);
			resultMap.put("msg", "系统出现异常，删除失败！");
			return resultMap;
		}
		resultMap.put("status", 200);
		resultMap.put("msg", "删除成功！");
		return resultMap;
	}
}