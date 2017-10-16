package com.bo.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 工具类
 * @author DengJinbo.
 * @Time 2017年7月12日
 * @Version 1.0
 */
public class T {
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getNow() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 获取指定日期的当月开始时间
	 * 例如：传入时间：2017-09-29 17:10:00，返回：2017-09-01 00:00:00
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getTheDay(cal.getTime());
	}
	
	/**
	 * 获取指定日期的当月结束时间
	 * 例如：传入时间：2017-09-29 17:10:00，返回：2017-09-30 23:59:59
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getTodayLast(cal.getTime());
	}
	
	/**
	 * 得到某一天的00:00:00时间
	 * 例如：传入时间：2017-09-29 17:10:00，返回：2017-09-29 00:00:00
	 * @param date
	 * @return
	 */
	public static Date getTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 得到某一天的23:59:59时间
	 * 例如：传入时间：2017-09-29 17:10:00，返回：2017-09-29 23:59:59
	 * @param date
	 * @return
	 */
	public static Date getTodayLast(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的月份天数
	 * 例如：传入时间：2017-09-29 17:10:00，返回：30
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {  
        Calendar calendar = Calendar.getInstance(Locale.CHINA);  
        calendar.setTime(date);  
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
    }
	
	/**
	 * 获取指定日期是当前月的第几天
	 * 例如：传入时间：2017-09-29 17:10:00，返回：29
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定日期是当前年的第几月
	 * 例如：传入时间：2017-10-04 17:10:00，返回：10
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.MONTH) + 1; // 返回的月是从 0--11，所以需要 +1
	}
	
	/**
	 * n分钟前或后 + -
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * n小时前或后 + -
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * n天前或后 + -
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * n月前或后 + -
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * n年前或后 + -
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);

		return new Date(cal.getTime().getTime());
	}
	
	//********* ********** ********** ********** 分隔符   ********** ********** ********** **********//
	
	/**
	 * 格式化日期
	 * @param date 需要格式化的时间
	 * @param fmt 需要的格式（例：yyyy-MM-dd）
	 * @return
	 */
	public static String format(Date date, String fmt) {
		if (date == null) {
			return "";
		}
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.format(date);
	}

	/**
	 * 格式化日期，格式：yyyy-MM-dd
	 * @param date 需要格式化的时间
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化当前日期，格式：yyyy-MM-dd
	 * @return
	 */
	public static String format() {
		return format(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
	}

	/**
	 * 格式化时间，精确到秒，格式：yyyy-MM-dd HH:mm:ss
	 * @param date 需要格式化的时间
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化当前时间，精确到秒，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateTime() {
		return format(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss");
	}
	
	//********* ********** ********** ********** 分隔符   ********** ********** ********** **********//
	
	/**
	 * 字符串转换数字类型
	 * @param str 需要转换的字符串
	 * @param def 默认返回值
	 * @return<br>
	 * @author DengJinbo, 2017年7月23日.<br>
	 */
	public static int intValue(String str, int def) {
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return Integer.parseInt(str.trim());
		} catch (Exception e) {
			return def;
		}
	}
	
	/**
	 * 字符串转换长整型
	 * @param str 需要转换的字符串
	 * @param def 默认返回值
	 * @return<br>
	 * @author DengJinbo, 2017年7月23日.<br>
	 */
	public static long longValue(String str, long def) {
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return Long.parseLong(str.trim());
		} catch (Exception e) {
			return def;
		}
	}
	
	/**
	 * 获取字符串，如果字符串内容为空，则返回默认值
	 * @param str 字符串
	 * @param def 默认值
	 * @return<br>
	 * @author DengJinbo, 2017年9月18日.<br>
	 */
	public static String stringValue(String str, String def) {
		if (str == null || str.length() == 0) {
			return def;
		}
		return str;
	}
	
	/**
	 * 字符串转换双精度浮点类型
	 * @param str 需要转换的字符串
	 * @param def 默认返回值
	 * @return<br>
	 * @author DengJinbo, 2017年9月26日.<br>
	 */
	public static double doubleValue(String str, double def) {
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return Double.parseDouble(str.trim());
		} catch (Exception e) {
			return def;
		}
	}
	
	/**
	 * 字符串转换Date类型
	 * @param str 需要转换的字符串
	 * @param fmt 转换的日期格式
	 * @param def 默认返回值
	 * @return<br>
	 * @author DengJinbo, 2017年9月26日.<br>
	 */
	public static Date dateValue(String str, String fmt, Date def) {
		if (str == null || str.length() == 0) {
			return def;
		}
		try {
			return new SimpleDateFormat(fmt).parse(str.trim());
		} catch (Exception e) {
			return def;
		}
	}
	
	//********* ********** ********** ********** 分隔符   ********** ********** ********** **********//
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}
	
	/**
	 * 获取MD5加密后的字符串
	 * @param text 需要加密的字符串
	 * @return
	 */
	public static String md5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(text.getBytes("utf-8"));
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				String b = Integer.toHexString(0xFF & digest[i]);
				if (b.length() == 1) {
					buf.append('0');
				}
				buf.append(b);
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * 获取应用URL
	 * @param request
	 * @return
	 */
	public static String getRoot(HttpServletRequest request) {
		String ROOT = Config.getDomain();
		if (isBlank(ROOT)) {
			ROOT = request.getHeader("host") + request.getContextPath();
		}
		return ROOT;
	}
	
	/**
	 * 获取当前页面URL
	 * @param request
	 * @return
	 */
	public static String getThisUrl(HttpServletRequest request) {
		String baseUrl = getRoot(request) + ((request.getContextPath() != null && request.getRequestURI() != null)
				? request.getRequestURI().replaceFirst(request.getContextPath(), "")
				: request.getRequestURI());
		String queryString = request.getQueryString();
		String qs = StringUtils.trimToNull(queryString);
		if (qs == null) {
			return baseUrl;
		} else {
			queryString = queryString.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			return baseUrl + "?" + queryString;
		}
	}
	
	/**
	 * 根据长度截取字符串
	 * @param str 要截取的字符串
	 * @param length 截取的长度
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String subString(String str, Integer length) {
		if (isBlank(str)) {
			return "";
		} else {
			str = str.trim();
			if (str.length() <= length) {
				return str;
			} else {
				return str.substring(0, length);
			}
		}
	}
	
	/**
	 * 根据长度截取字符串,大于该长度替换为指定字符
	 * @param str 要截取的字符串
	 * @param length 截取的长度
	 * @param replaceStr 替换字符
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String subString(String str, Integer length, String replaceStr) {
		if (isBlank(str)) {
			return "";
		} else {
			str = str.trim();
			if (str.length() <= length) {
				return str;
			} else {
				return str.substring(0, length) + replaceStr;
			}
		}
	}
}