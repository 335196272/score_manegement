package com.bo.score.service;

import com.bo.common.service.BaseService;
import com.bo.score.entity.Classes;

/**
 * 班级业务接口
 * @author DengJinbo.
 * @Time 2017年10月17日
 */
public interface ClassesService extends BaseService<Classes> {

	/**
	 * 根据班级名称查询班级
	 * @param name 班级名称
	 * @return
	 * @author DengJinbo, 2017年10月17日.<br>
	 */
	Classes findByName(String name);

}
