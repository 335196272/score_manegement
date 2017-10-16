package com.bo.common.enums;

/**
 * 状态枚举
 * @author DengJinbo.
 * @Time 2017年9月14日
 */
public enum StatusEnum {
	
	STATUS_CLOSE(-1, "禁用"), STATUS_DRAFT(0, "草稿"), 
	STATUS_NORMAL(1, "启用"), STATUS_NONEED(-99, "无效");

	/**
	 * 索引
	 */
	private int index;
	
	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 构造函数
	 * @param index
	 * @param desc
	 */
	StatusEnum(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

	/**
	 * getIndex
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * getDesc
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
        
	/**
	 * 根据索引获取常量
	 * @param index
	 * @return
	 */
	public static StatusEnum getIndex(int index) {
		for (StatusEnum e : StatusEnum.values()) {
			if (e.getIndex() == index) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * 根据索引获取描述
	 * @param index
	 */
	public static String getDesc(int index) {
		StatusEnum e = StatusEnum.getIndex(index);
        return (null != e) ? e.getDesc() : "";
	}
	
	/**
	 * 根据索引获取带HTML标签的描述
	 * @param index
	 */
	public static String getHtmlDesc(int index) {
		String result = null;
		switch (index) {
		case -99:
			result = "<input class='btn btn-warning radius' type='button' value='无效'>";
			break;
		case -1:
			result = "<input class='btn btn-danger radius' type='button' value='禁用'>"; 
			break;
		case 0:
			result = "<input class='btn btn-default radius' type='button' value='草稿'>";
			break;
		case 1:
			result = "<input class='btn btn-success radius' type='button' value='启用'>";
			break;
		}
		return result;
	}
}