package com.bo.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bo.common.entity.User;
import com.bo.common.enums.LogTypeEnum;
import com.bo.common.enums.StatusEnum;
import com.bo.common.service.LogService;
import com.bo.common.service.UserService;
import com.bo.common.util.FileUtils;
import com.bo.common.util.IpUtils;
import com.bo.common.util.T;

/**
 * 用户控制器
 * @author DengJinbo.
 * @Time 2017年9月8日
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private LogService logService;

	/**
	 * 检查用户名是否存在
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUserName.html", method = RequestMethod.GET)
	public Map<String, Object> checkUserName(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userName = req.getParameter("userName");
		User user = userService.findByAccount(userName);
		if (user != null) {
			resultMap.put("msg", "true");
		} else {
			resultMap.put("msg", "false");
		}
		return resultMap;
	}

	/**
	 * 检查手机号是否已经存在
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPhoneNumber.html", method = RequestMethod.GET)
	public Map<String, Object> checkPhoneNumber(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String phoneNumber = req.getParameter("phoneNumber");
		User user = userService.findByAccount(phoneNumber);
		if (user != null) {
			resultMap.put("msg", "true");
		} else {
			resultMap.put("msg", "false");
		}
		return resultMap;
	}

	/**
	 * 检查邮箱账号是否已经存在
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/checkEmail.html", method = RequestMethod.GET)
	public Map<String, Object> checkEmail(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String email = req.getParameter("email");
		User user = userService.findByAccount(email);
		if (user != null) {
			resultMap.put("msg", "true");
		} else {
			resultMap.put("msg", "false");
		}
		return resultMap;
	}

	/**
	 * 用户注册
	 * @param req
	 * @param session
	 * @return<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public Map<String, Object> register(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userName = req.getParameter("userName");
		String phoneNumber = req.getParameter("phoneNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Date registerTime = T.getNow();
		String defaultPic = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
		+ req.getContextPath() + "/images/user/default.gif"; //默认用户头像

		User user = new User();
		user.setUserName(userName);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setPicUrl(defaultPic);
		user.setPassword(T.md5(userName + password + registerTime)); // 加盐
		user.setCreateDate(registerTime);
		user.setStatus(StatusEnum.STATUS_NORMAL.getIndex()); // 默认正常状态
		user.setUpdateDate(registerTime);
		user.setLastLoginDate(registerTime);
		userService.create(user);

		logService.log(user.getUserName(), LogTypeEnum.CREATE.getDesc(), "用户：" 
				+ user.getUserName() + "，注册成功。", IpUtils.getIp());
		session.setAttribute("currentUser", user);
		resultMap.put("status", 200);
		return resultMap;
	}

	/**
	 * 用户登录
	 * @param req
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月15日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("rememberMe");

		User findUser = userService.findByAccount(account);
		if (findUser == null) {
			resultMap.put("status", 300);
		} else {
			password = T.md5(findUser.getUserName() + password + findUser.getCreateDate()); // 加盐
			User currentUser = userService.login(account, password);
			if (currentUser == null) {
				resultMap.put("status", 300);
			} else {
				currentUser.setLastLoginDate(T.getNow());
				userService.update(currentUser);
				logService.log(currentUser.getUserName(), LogTypeEnum.LOGIN.getDesc(), "用户：" 
						+ currentUser.getUserName() + "，登录系统。", IpUtils.getIp());
				if (rememberMe.equals("true")) { // 7天免登录
					session.setMaxInactiveInterval(604800);
				}
				session.setAttribute("currentUser", currentUser);
				resultMap.put("status", 200);
			}
		}
		return resultMap;
	}

	/**
	 * 用户登出
	 * @param session
	 * @return<br>
	 * @author DengJinbo, 2017年9月15日.<br>
	 */
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/admin/login.html";
	}
	
	/**
	 * 跳转修改密码页面
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@RequestMapping(value = "/toModifyPassword.html", method = RequestMethod.GET)
	public String toModifyPassword() {
		return "admin/user/modifyPassword";
	}
	
	/**
	 * 修改密码
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyPassword.html", method = RequestMethod.GET)
	public Map<String, Object> modifyPassword(HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String oldPassword = T.stringValue(req.getParameter("oldPassword"), null);
		String password = T.stringValue(req.getParameter("password"), null);
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		
		if (oldPassword.equals(password)) {
			resultMap.put("status", 300);
			resultMap.put("msg", "新密码不能和旧密码一样，请重新输入！");
		} else {
			oldPassword = T.md5(currentUser.getUserName() + oldPassword + currentUser.getCreateDate());
			if (!oldPassword.equals(currentUser.getPassword())) {
				resultMap.put("status", 300);
				resultMap.put("msg", "旧密码错误，请重新输入！");
			} else {
				password = T.md5(currentUser.getUserName() + password + currentUser.getCreateDate());
				currentUser.setPassword(password);
				userService.update(currentUser);
				logService.log(currentUser.getUserName(), LogTypeEnum.UPDATE.getDesc(), "修改密码成功！", IpUtils.getIp(req));
				
				resultMap.put("status", 200);
				resultMap.put("msg", "密码修改成功！");
			}
		}
		return resultMap;
	}
	
	/**
	 * 跳转用户信息页面
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	@RequestMapping(value = "/toUserInfo.html", method = RequestMethod.GET)
	public String toUserInfo(HttpServletRequest req) {
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		req.setAttribute("entity", currentUser);
		return "admin/user/userInfo";
	}
	
	/**
	 * 用户头像上传
	 * @param file 上传图片文件
	 * @param req
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	@ResponseBody
    @RequestMapping("/uploadImg.html")
	public Map<String, Object> uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		long currentUserId = currentUser.getUserId();
		int uploadCount = 0;
		try {
			uploadCount = (int) req.getSession().getAttribute(currentUserId + "uploadCount"); // 获取当前用户头像上传次数
		} catch (Exception e) { }
		if (uploadCount >= 50) {
			resultMap.put("status", 300);
			resultMap.put("msg", "请勿频繁上传图片！");
			return resultMap;
		}
		String fileName = file.getOriginalFilename(); // 获取文件名加后缀
		if (T.isBlank(fileName)) {
			resultMap.put("status", 300);
			resultMap.put("msg", "获取上传文件名为空！");
			return resultMap;
		}
		String picUrl = FileUtils.uploadImg(req, "user/" + currentUserId, file, fileName, currentUserId);
		if (T.isBlank(picUrl)) {
			resultMap.put("status", 300);
			resultMap.put("msg", "系统发生错误，文件上传失败！");
			return resultMap;
		}
		session.setAttribute(currentUserId + "uploadCount", uploadCount++);
		resultMap.put("status", 200);
		resultMap.put("msg", picUrl);
		return resultMap;
	}
	
	/**
	 * 修改用户信息
	 * @param req
	 * @param session
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyUserInfo.html", method = RequestMethod.POST)
	public Map<String, Object> modifyUserInfo(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userName = T.stringValue(req.getParameter("userName"), null);
		String phoneNumber = T.stringValue(req.getParameter("phoneNumber"), null);
		String email = T.stringValue(req.getParameter("email"), null);
		String picUrl = T.stringValue(req.getParameter("picUrl"), null);
		
		User currentUser = (User) req.getSession().getAttribute("currentUser"); // 获取当前登录用户
		currentUser.setUserName(userName);
		currentUser.setPhoneNumber(phoneNumber);
		currentUser.setEmail(email);
		currentUser.setPicUrl(picUrl);
		currentUser.setUpdateDate(T.getNow());
		userService.update(currentUser);

		String fileName = picUrl.substring(picUrl.lastIndexOf("/") + 1, picUrl.length());
		FileUtils.deletImg(req, "user/" + currentUser.getUserId(), fileName);
		logService.log(currentUser.getUserName(), LogTypeEnum.UPDATE.getDesc(), "用户：" 
				+ currentUser.getUserName() + "，修改个人信息。", IpUtils.getIp());
		session.setAttribute("currentUser", currentUser);
		resultMap.put("status", 200);
		resultMap.put("msg", "个人信息修改成功！");
		return resultMap;
	}
}