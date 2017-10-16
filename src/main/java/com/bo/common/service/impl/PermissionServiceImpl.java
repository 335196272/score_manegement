package com.bo.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.PermissionDao;
import com.bo.common.entity.Permission;
import com.bo.common.service.PermissionService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 权限Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public BaseDao<Permission> getDao() {
		return permissionDao;
	}
	
	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static PermissionServiceImpl instance() {
		return (PermissionServiceImpl) SpringBeanFactoryUtil.getBean("permissionService");
	}

	/**
	 * 根据角色ID查询角色拥有的权限列表
	 * @param roleId
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	@Override
	public List<Permission> listPermissionsByRoleId(Long roleId) {
		return permissionDao.listPermissionsByRoleId(roleId);
	}

}
