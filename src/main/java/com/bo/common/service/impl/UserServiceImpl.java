package com.bo.common.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.UserDao;
import com.bo.common.entity.User;
import com.bo.common.service.UserService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 用户Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public BaseDao<User> getDao() {
		return userDao;
	}
	
	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static UserServiceImpl instance() {
		return (UserServiceImpl) SpringBeanFactoryUtil.getBean("userService");
	}

	/**
	 * 根据账号信息查询用户
	 * @param account 用户名/手机号/邮箱
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	@Override
	public User findByAccount(String account) {
		return userDao.findByAccount(account);
	}

	/**
	 * 用户登录验证
	 * @param account 用户名/手机号/邮箱
	 * @param password 加盐后的密码
	 * @return<br>
	 * @author DengJinbo, 2017年9月15日.<br>
	 */
	@Override
	public User login(String account, String password) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("account", account);
		parameterMap.put("password", password);
		return userDao.login(parameterMap);
	}

	/**
	 * 获取正常状态用户列表
	 * @return<br>
	 * @author DengJinbo, 2017年9月25日.<br>
	 */
	@Override
	public List<User> listUser() {
		return userDao.listUser();
	}
}
