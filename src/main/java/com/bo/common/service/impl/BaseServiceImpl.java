package com.bo.common.service.impl;

import java.util.HashMap;
import java.util.List;

import com.bo.common.dao.BaseDao;
import com.bo.common.service.BaseService;
import com.bo.common.util.Pager;

/**
 * 基础Service接口实现类，其他的自定义 ServiceImpl继承自它,可以获得常用的增删查改操作
 * @author DengJinbo.
 * @Time 2017年9月4日
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	/**
	 * 定义成抽象方法,由子类实现,完成dao的注入
	 * @return<br> BaseDao实现类
	 */
	public abstract BaseDao<T> getDao();
	
	/**
     * 创建对象
     * @param obj
     * @return<br>
     */
    public int create(T obj) {
		return getDao().create(obj);
    }
    
    /**
     * 根据ID删除对象
     * @param id
     * @return<br>
     */
    public int delete(Object id) {
		return getDao().delete(id);
    }
	
    /**
     * 修改对象
     * @param obj
     * @return<br>
     */
    public int update(T obj) {
		return getDao().update(obj);
    }
    
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return<br>
	 */
    public T find(Object id) {
		return getDao().find(id);
    	
    }
    
    /**
     * 根据条件获取记录数
     * @param parameterMap
     * @return<br>
     */
	public int count(HashMap<String, Object> parameterMap) {
		return getDao().count(parameterMap);
		
	}

	/**
	 * 根据条件获取列表
	 * @param parameterMap
	 * @return<br>
	 */
	public List<T> list(HashMap<String, Object> parameterMap) {
		return getDao().list(parameterMap);
	}
	
	/**
	 * 根据条件获取分页对象
	 * @param parameterMap
	 * @return<br>
	 */
	public Pager<T> pager(HashMap<String, Object> parameterMap) {
		Pager<T> pager = new Pager<T>();
		pager.setPageNo((Integer) parameterMap.get("pageNum"));
		pager.setPageSize((Integer) parameterMap.get("numPerPage"));
		pager.setTotal(getDao().count(parameterMap));
		pager.setResultList(getDao().list(parameterMap));
		return pager;
	}
}