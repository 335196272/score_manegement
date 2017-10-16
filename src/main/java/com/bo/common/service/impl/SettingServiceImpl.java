package com.bo.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.SettingDao;
import com.bo.common.entity.Setting;
import com.bo.common.service.SettingService;
import com.bo.common.util.SpringBeanFactoryUtil;

/**
 * 系统配置Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月5日
 */
@Service("settingService")
public class SettingServiceImpl extends BaseServiceImpl<Setting> implements SettingService {

	@Resource
	private SettingDao settingDao;
	
	@Override
	public BaseDao<Setting> getDao() {
		return settingDao;
	}

	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static SettingService instance() {
		return (SettingService) SpringBeanFactoryUtil.getBean("settingService");
	}
}
