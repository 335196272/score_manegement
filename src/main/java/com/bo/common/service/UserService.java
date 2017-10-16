package com.bo.common.service;

import java.util.List;

import com.bo.common.entity.User;

/**
 * 用户业务接口
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
public interface UserService extends BaseService<User> {

	/**
	 * 根据账号信息查询用户
	 * @param account 用户名/手机号/邮箱
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	User findByAccount(String account);

	/**
	 * 用户登录验证
	 * @param account 用户名/手机号/邮箱
	 * @param password 加盐后的密码
	 * @return<br>
	 * @author DengJinbo, 2017年9月15日.<br>
	 */
	User login(String account, String password);

	/**
	 * 获取正常状态用户列表
	 * @return<br>
	 * @author DengJinbo, 2017年9月25日.<br>
	 */
	List<User> listUser();
}
