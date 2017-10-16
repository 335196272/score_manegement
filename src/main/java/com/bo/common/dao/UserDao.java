package com.bo.common.dao;

import java.util.HashMap;
import java.util.List;

import com.bo.common.entity.User;

/**
 * 系统用户DAO接口
 * @author DengJinbo.
 * @Time 2017年9月4日
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * 根据账号信息查询用户
	 * @param account 用户名/手机号/邮箱
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	User findByAccount(String account);

	/**
	 * 用户登录验证
	 * @param parameterMap 参数集合
	 * @return<br>
	 * @author DengJinbo, 2017年9月15日.<br>
	 */
	User login(HashMap<String, Object> parameterMap);

	/**
	 * 获取正常状态用户列表
	 * @return<br>
	 * @author DengJinbo, 2017年9月25日.<br>
	 */
	List<User> listUser();

}