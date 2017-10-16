package com.bo.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bo.common.dao.BaseDao;
import com.bo.common.dao.LogDao;
import com.bo.common.entity.Log;
import com.bo.common.service.LogService;
import com.bo.common.util.SpringBeanFactoryUtil;
import com.bo.common.util.T;

/**
 * 系统日志Service接口实现类
 * @author DengJinbo.
 * @Time 2017年9月4日
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Resource
	private LogDao logDao;
	
	@Override
	public BaseDao<Log> getDao() {
		return logDao;
	}
	
	/**
	 * 获取实例方法
	 * @return<br>
	 * @author DengJinbo, 2017年9月19日.<br>
	 */
	public static LogServiceImpl instance() {
		return (LogServiceImpl) SpringBeanFactoryUtil.getBean("logService");
	}

	/**
	 * 记录操作日志
     * @param userName - 操作用户
     * @param action - 操作，从LogTypeEnum中获取
     * @param detail - 操作详情
	 * @param ip - 操作IP<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	@Override
	public void log(String userName, String action, String detail, String ip) {
		Log log = new Log();
        log.setName(userName);
        log.setAction(action);
        log.setCreateDate(T.getNow());
        log.setDetail(detail);
        log.setIp(ip);
        logDao.create(log);
	}

}
