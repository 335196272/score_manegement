package com.bo.common.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bo.common.entity.Log;
import com.bo.common.service.LogService;
import com.bo.common.util.Pager;
import com.bo.common.util.T;

/**
 * 系统日志控制器
 * @author DengJinbo.
 * @Time 2017年9月17日
 */
@Controller
@RequestMapping("/admin/log")
public class LogController {

	@Resource
	private LogService logService;

	/**
	 * 列表
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月17日.<br>
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest req) {
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 15);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String name = T.stringValue(req.getParameter("name"), null);
		String ge_createDate = T.stringValue(req.getParameter("ge_createDate"), null);
		String le_createDate = T.stringValue(req.getParameter("le_createDate"), null);
		String detail = T.stringValue(req.getParameter("detail"), null);
		String ip = T.stringValue(req.getParameter("ip"), null);
		String action = T.stringValue(req.getParameter("action"), null);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "create_date" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
		parameterMap.put("name", name);
		parameterMap.put("ge_createDate", ge_createDate);
		parameterMap.put("le_createDate", le_createDate);
		parameterMap.put("detail", detail);
		parameterMap.put("ip", ip);
		parameterMap.put("action", action);
		Pager<Log> pager = logService.pager(parameterMap);
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
    	return "admin/log/list"; 
    }
}
