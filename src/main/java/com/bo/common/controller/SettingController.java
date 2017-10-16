package com.bo.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bo.common.entity.Setting;
import com.bo.common.entity.User;
import com.bo.common.enums.LogTypeEnum;
import com.bo.common.service.LogService;
import com.bo.common.service.SettingService;
import com.bo.common.util.IpUtils;
import com.bo.common.util.Pager;
import com.bo.common.util.T;

/**
 * 系统设置控制器
 * @author DengJinbo.
 * @Time 2017年9月18日
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController {

	@Resource
	private SettingService settingService;
	
	@Resource
	private LogService logService;

	/**
	 * 列表
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String list(HttpServletRequest req) {
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
		int numPerPage = T.intValue(req.getParameter("numPerPage"), 15);
		String orderField = T.stringValue(req.getParameter("orderField"), null);
		String orderDirection = T.stringValue(req.getParameter("orderDirection"), null);
		String name = T.stringValue(req.getParameter("name"), null);
		String description = T.stringValue(req.getParameter("description"), null);
		String ge_updateDate = T.stringValue(req.getParameter("ge_updateDate"), null);
		String le_updateDate = T.stringValue(req.getParameter("le_updateDate"), null);
		
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("pageNum", pageNum);
		parameterMap.put("numPerPage", numPerPage);
		parameterMap.put("startRow", (pageNum - 1) * numPerPage);
		parameterMap.put("orderField", T.isBlank(orderField) ? "update_date" : orderField);
		parameterMap.put("orderDirection", T.isBlank(orderDirection) ? "desc" : orderDirection);
		parameterMap.put("name", name);
		parameterMap.put("description", description);
		parameterMap.put("ge_updateDate", ge_updateDate);
		parameterMap.put("le_updateDate", le_updateDate);
		Pager<Setting> pager = settingService.pager(parameterMap);
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("total", pager.getTotal());
		req.setAttribute("list", pager.getResultList());
    	return "admin/setting/list"; 
    }
	
	/**
	 * 跳转新增页面
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@RequestMapping(value = "/toAdd.html", method = RequestMethod.GET)
    public String toAdd() {
    	return "admin/setting/add"; 
    }
	
	/**
	 * 新增
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String name = T.stringValue(req.getParameter("name"), null);
		String value = T.stringValue(req.getParameter("value"), null);
		String description = T.stringValue(req.getParameter("description"), null);
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		Setting setting = new Setting();
		setting.setName(name);
		setting.setValue(value);
		setting.setDescription(description);
		setting.setCreateBy(currentUser.getUserId());
		setting.setCreateDate(T.getNow());
		setting.setUpdateBy(currentUser.getUserId());
		setting.setUpdateDate(T.getNow());
		settingService.create(setting);
		logService.log(currentUser.getUserName(), LogTypeEnum.CREATE.getDesc(), "新增系统设置！", IpUtils.getIp(req));
		
		resultMap.put("status", 200);
		resultMap.put("msg", "创建成功！");
		return resultMap;
	}
	
	/**
	 * 跳转修改页面
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@RequestMapping(value = "/toModify.html", method = RequestMethod.GET)
    public String toModify(HttpServletRequest req) {
		long settingId = T.longValue(req.getParameter("settingId"), 0);
		Setting setting = settingService.find(settingId);
		req.setAttribute("entity", setting);
    	return "admin/setting/modify"; 
    }
	
	/**
	 * 修改
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/modify.html", method = RequestMethod.POST)
	public Map<String, Object> modify(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		long settingId = T.longValue(req.getParameter("settingId"), 0);
		String name = T.stringValue(req.getParameter("name"), null);
		String value = T.stringValue(req.getParameter("value"), null);
		String description = T.stringValue(req.getParameter("description"), null);
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		Setting setting = settingService.find(settingId);
		setting.setName(name);
		setting.setValue(value);
		setting.setDescription(description);
		setting.setUpdateBy(currentUser.getUserId());
		setting.setUpdateDate(T.getNow());
		settingService.update(setting);
		logService.log(currentUser.getUserName(), LogTypeEnum.UPDATE.getDesc(), "修改系统设置！", IpUtils.getIp(req));
		
		resultMap.put("status", 200);
		resultMap.put("msg", "修改成功！");
		return resultMap;
	}
	
	/**
	 * 删除
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		long settingId = T.longValue(req.getParameter("settingId"), 0);
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		Setting setting = settingService.find(settingId);
		if (setting != null) {
			settingService.delete(settingId);
			logService.log(currentUser.getUserName(), LogTypeEnum.DELETE.getDesc(), "删除系统设置！", IpUtils.getIp(req));
			resultMap.put("status", 200);
			resultMap.put("msg", "删除成功！");
		} else {
			resultMap.put("status", 300);
			resultMap.put("msg", "删除失败，系统设置不存在！");
		}
		return resultMap;
	}
}