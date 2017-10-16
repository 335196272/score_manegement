package com.bo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共视图控制器
 * @author DengJinbo.
 * @Time 2017年9月8日
 */
@Controller
public class CommonController {
    
	/**
	 * 后台首页
	 * @return<br>
	 * @author DengJinbo, 2017年9月8日.<br>
	 */
    @RequestMapping("/admin/index.html")
    public String index() {
        return "admin/index";
    }

    /**
     * 后台登录页
     * @return<br>
     * @author DengJinbo, 2017年9月8日.<br>
     */
    @RequestMapping("/admin/login.html")
    public String login() {
        return "admin/login";
    }
    
    /**
     * 后台注册页
     * @return<br>
     * @author DengJinbo, 2017年9月14日.<br>
     */
    @RequestMapping("/admin/register.html")
    public String register() {
        return "admin/register";
    }
    
    /**
     * 后台欢迎页面
     * @return<br>
     * @author DengJinbo, 2017年9月18日.<br>
     */
    @RequestMapping("/admin/welcome.html")
    public String welcome() {
    	return "admin/welcome";
    }
}