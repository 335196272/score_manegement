package com.bo.common.service;

import java.util.HashMap;
import java.util.List;

import com.bo.common.util.Pager;

/**
 * 基础Service接口，封装常用的增删改查操作
 * @author DengJinbo.
 * @Time 2017年9月4日
 * @param <T>
 */
public interface BaseService<T> {

	/**
     * 创建对象
     * @param obj
     * @return<br>
     */
    int create(T obj);
    
    /**
     * 根据ID删除对象
     * @param id
     * @return<br>
     */
    int delete(Object id);
	
    /**
     * 修改对象
     * @param obj
     * @return<br>
     */
    int update(T obj);
    
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return<br>
	 */
    T find(Object id);
    
    /**
     * 根据条件获取记录数
     * @param parameterMap
     * @return<br>
     */
	int count(HashMap<String, Object> parameterMap);

	/**
	 * 根据条件获取列表
	 * @param parameterMap
	 * @return<br>
	 */
	List<T> list(HashMap<String, Object> parameterMap);
	
	/**
	 * 根据条件获取分页对象
	 * @param parameterMap
	 * @return<br>
	 */
	Pager<T> pager(HashMap<String, Object> parameterMap);
}
