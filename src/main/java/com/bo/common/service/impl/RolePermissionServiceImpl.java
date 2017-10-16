package com.bo.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.RolePermissionDao;
import com.bo.common.entity.RolePermission;
import com.bo.common.service.RolePermissionService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 角色权限Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

	@Resource
	private RolePermissionDao rolePermissionDao;

	@Override
	public BaseDao<RolePermission> getDao() {
		return rolePermissionDao;
	}

	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static RolePermissionServiceImpl instance() {
		return (RolePermissionServiceImpl) SpringBeanFactoryUtil.getBean("rolePermissionService");
	}
}
