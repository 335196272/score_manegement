package com.bo.score.controller;

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
import com.bo.score.entity.Classes;
import com.bo.score.service.ClassesService;

/**
 * 班级控制器
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
@Controller
@RequestMapping("/admin/classes")
public class ClassesController {

	@Resource
	private ClassesService classesService;
	
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
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "update_date" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
		parameterMap.put("name", name);
		Pager<Classes> pager = classesService.pager(parameterMap);
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
    	return "admin/classes/list"; 
    }
	
	/**
	 * 跳转新增页面
	 * @return<br>
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	@RequestMapping(value = "/toAdd.html", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest req) {
    	return "admin/classes/add"; 
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
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		Classes findClasses = classesService.findByName(name);
		if (findClasses != null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "班级已经存在，请不要重复创建！");
			return resultMap;
		}
		Classes entity = new Classes();
		entity.setName(name);
		entity.setCreateBy(currentUser.getUserId());
		entity.setCreateDate(T.getNow());
		entity.setUpdateBy(currentUser.getUserId());
		entity.setUpdateDate(T.getNow());
		classesService.create(entity);
		
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
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		Classes entity = classesService.find(classesId);
		req.setAttribute("entity", entity);
    	return "admin/classes/modify"; 
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
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		String name = T.stringValue(req.getParameter("name"), null);
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		
		Classes entity = classesService.find(classesId);
		if (entity == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "修改失败，班级不存在！");
			return resultMap;
		}
		Classes findClasses = classesService.findByName(name);
		if (findClasses != null && findClasses.getClassesId() != classesId) {
			resultMap.put("status", 300);
			resultMap.put("msg", "班级已经存在，不允许修改！");
			return resultMap;
		}
		entity.setName(name);
		entity.setUpdateBy(currentUser.getUserId());
		entity.setUpdateDate(T.getNow());
		classesService.update(entity);
		
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
		long classesId = T.longValue(req.getParameter("classesId"), 0);
		User currentUser = (User) req.getSession().getAttribute("currentUser");
		
		Classes entity = classesService.find(classesId);
		if (entity == null) {
			resultMap.put("status", 300);
			resultMap.put("msg", "删除失败，班级不存在！");
			return resultMap;
		}
		try {
			logService.log(currentUser.getUserName(), LogTypeEnum.DELETE.getDesc(), 
					"删除班级：“" + entity.getName() + "”！", IpUtils.getIp(req));
			classesService.delete(classesId);
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