package com.bo.common.enums;

/**
 * 日志类型枚举
 * @author DengJinbo.
 * @Time 2017年9月14日
 */
public enum LogTypeEnum {
	
	CREATE(1, "创建内容"), UPDATE(2, "修改内容"), MODIFY_STATUS(3, "更新状态"),
	DELETE(4, "删除内容"), PHYSICAL_DELETE(5, "物理删除"), LOGIN(6, "用户登录"),
	TASK(7, "定时任务"), API_INVOKE(8, "接口调用");

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
	LogTypeEnum(int index, String desc) {
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
	public static LogTypeEnum getIndex(int index) {
		for (LogTypeEnum e : LogTypeEnum.values()) {
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
		LogTypeEnum e = LogTypeEnum.getIndex(index);
        return (null != e) ? e.getDesc() : "";
	}
}