package com.bo.common.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 基础实体类
 * 
 * @author DengJinbo.
 * @Time 2017年9月1日
 * @Version 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {

	/**
	 * 通用的toString函数
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			sb.append(this.getClass().getSimpleName()).append("{");
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				sb.append(field.getName());
				sb.append(":");
				if (field.getType() == Integer.class) {
					sb.append(field.getInt(this));
				} else if (field.getType() == Long.class) {
					sb.append(field.getLong(this));
				} else if (field.getType() == Boolean.class) {
					sb.append(field.getBoolean(this));
				} else if (field.getType() == char.class) {
					sb.append(field.getChar(this));
				} else if (field.getType() == Double.class) {
					sb.append(field.getDouble(this));
				} else if (field.getType() == Float.class) {
					sb.append(field.getFloat(this));
				} else if (field.getType() == String.class) {
					String text = (String) field.get(this);
					if (text != null && text.length() > 100) {
						sb.append(text.substring(0, 100)).append("...");
					} else {
						sb.append(text);
					}
				} else {
					sb.append(field.get(this));
				}
				if (i < fields.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("}");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("generate entity toString failed.");
		}
		return sb.toString();
	}
}