package com.bo.common.service;

import com.bo.common.entity.Log;

/**
 * 系统日志业务接口
 * @author DengJinbo.
 * @Time 2017年9月4日
 */
public interface LogService extends BaseService<Log> {

	/**
	 * 记录操作日志
     * @param userName - 操作用户
     * @param action - 操作，从LogTypeEnum中获取
     * @param detail - 操作详情
	 * @param ip - 操作IP<br>
	 * @author DengJinbo, 2017年9月14日.<br>
	 */
	void log(String userName, String action, String detail, String ip);

}
