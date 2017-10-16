package com.bo.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring容器工具类
 * 当一个类实现了这个接口ApplicationContextAware之后，这个类就可以方便获得ApplicationContext中的所有bean。
 * 换句话说，这个类可以直接获取spring配置文件中所有有引用到的bean对象
 * 前提条件需作为一个普通的bean在spring的配置文件中进行注册 
 * @author DengJinbo.
 * @Time 2017年7月4日
 * @Version 1.0
 */
public class SpringBeanFactoryUtil implements ApplicationContextAware  {
	
	private static ApplicationContext appCtx;

	/**
	 * 将ApplicationContext对象inject到当前类中作为一个静态成员变量。
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		try {
			appCtx = applicationContext;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
	  * 获取ApplicationContext
	  * @return<br>
	  * @author DengJinbo, 2017年7月4日.<br>
	  */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    /**
     * 根据名称获取Bean
     * @param beanName
     * @return<br>
     * @author DengJinbo, 2017年7月4日.<br>
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
}