package com.bo.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.RoleDao;
import com.bo.common.entity.Role;
import com.bo.common.service.RoleService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 角色Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public BaseDao<Role> getDao() {
		return roleDao;
	}
	
	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static RoleServiceImpl instance() {
		return (RoleServiceImpl) SpringBeanFactoryUtil.getBean("roleService");
	}

	/**
	 * 根据用户ID查询用户角色列表
	 * @param userId
	 * @return<br>
	 * @author DengJinbo, 2017年9月7日.<br>
	 */
	@Override
	public List<Role> listRolesByUserId(Long userId) {
		return roleDao.listRolesByUserId(userId);
	}

}
