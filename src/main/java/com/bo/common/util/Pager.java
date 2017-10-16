package com.bo.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author DengJinbo.
 * @Time 2017年9月17日
 */
@SuppressWarnings({ "serial", "hiding" })
public class Pager<T> implements Serializable {
	
	/**
	 * 页号
	 */
	private int pageNo;
	
	/**
	 * 每页数量
	 */
	private int pageSize;
	
	/**
	 * 总数量
	 */
	private int total;
	
	/**
	 * 是否为所有列表
	 */
	private boolean isAllList = false;

	/**
	 * 查询后的结果集
	 */
	private List<T> resultList;

	/**
	 * 无参构造函数
	 */
	public Pager() { }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Pager(List resultList) {
		// 这个是给查出全部结果集的list用的构建方法
		this.resultList = resultList;
		isAllList = true;
		if (resultList != null && !resultList.isEmpty()) {
			total = resultList.size();
		}
	}
	
	/**
	 * 总页数
	 */
	public int getPageCount() {
		return total == 0 ? 1 : total / pageSize + (total % pageSize == 0 ? 0 : 1);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResultList() {
		if (isAllList && resultList != null && !resultList.isEmpty()) {
			int fromIndex = (pageNo - 1) * pageSize;
			int toIndex = pageNo * pageSize;
			if (fromIndex >= total) {
				return null;
			} else if (toIndex > total) {
				toIndex = total;
			}
			return resultList.subList(fromIndex, toIndex);
		}
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}