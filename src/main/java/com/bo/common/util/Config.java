package com.bo.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * 读取配置文件内容
 * @author DengJinbo.
 * @Time 2017年7月12日
 * @Version 1.0
 */
public class Config {
	
	/**
	 * 根据配置名获取配置内容
	 * @param name
	 * @return<br>
	 * @author DengJinbo, 2017年7月22日.<br>
	 */
	public static String getConfig(String name) {
		Properties prop = new Properties();
		String value = "";
		try {
			// 读取属性配置文件
			File file = new File(
					new File(Config.class.getResource("/").toURI()).getParentFile().getParentFile().getAbsolutePath()
							+ "/WEB-INF/classes/system.properties");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			prop.load(in); // 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext() && T.isBlank(value)) {
				value = prop.getProperty(name);
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}
	
	/**
	 * 直接读取props配置文件内容
	 * @param name - 参数名
	 * @param def - 默认值
	 * @return
	 */
	public static String getConfig(String name, String def) {
		String value = getConfig(name);
		if (T.isBlank(value)) {
			return def;
		} else {
			return value;
		}
	}

	/**
	 * 获取主机域名地址
	 * @return
	 */
	public static String getDomain() {
		return getConfig("sys.host");
	}
}