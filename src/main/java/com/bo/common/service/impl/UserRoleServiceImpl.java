package com.bo.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.UserRoleDao;
import com.bo.common.entity.UserRole;
import com.bo.common.service.UserRoleService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 用户角色Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

	@Resource
	private UserRoleDao userRoleDao;
	
	@Override
	public BaseDao<UserRole> getDao() {
		return userRoleDao;
	}

	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static UserRoleServiceImpl instance() {
		return (UserRoleServiceImpl) SpringBeanFactoryUtil.getBean("userRoleService");
	}
}
