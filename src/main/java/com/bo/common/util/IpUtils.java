package com.bo.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * @author DengJinbo.
 * @Time 2017年8月11日
 * @Version 1.0
 */
public class IpUtils {

	/**
	 * 获取IP全地址,包括代理IP和局域网IP
	 * @param request
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded;
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
			}
		}
		if (ip.length() > 95) {
			System.err.println("ip:" + ip);
			ip = ip.substring(0, 95);
		}
		return ip;
	}

	/**
	 * 获取公网IP/代理IP
	 * @param request
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.indexOf(", ") > -1) {
			// ip.lastIndexOf(", ");
			ip = ip.substring(ip.lastIndexOf(", ") + 2);
		}
		return ip;
	}

	private static String localIp;
	static {
		localIp = getLocalAddress();
	}

	/**
	 * 获取本地IP地址
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String getIp() {
		return localIp;
	}

	/**
	 * 获取本地IP地址
	 * @return<br>
	 * @author DengJinbo, 2017年8月11日.<br>
	 */
	public static String getLocalAddress() {
		String result = null;
		Enumeration<NetworkInterface> enu = null;
		try {
			enu = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			return "localhost";
		}
		while (enu.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) (enu.nextElement());
			Enumeration<InetAddress> ias = ni.getInetAddresses();
			while (ias.hasMoreElements()) {
				InetAddress i = (InetAddress) (ias.nextElement());
				String addr = i.getHostAddress();
				if (addr.startsWith("192.")) {
					return addr;
				}
			}
		}
		return result;
	}

	/**
	 * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	 * @param strIp
	 * @return
	 */
	public static long ipToLong(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 *  将十进制整数形式转换成127.0.0.1形式的ip地址
	 * @param longIp
	 * @return
	 */
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	public static void main(String[] args) {
		String ip = "192.168.20.112, 192.168.182.145";
		if (ip != null && ip.indexOf(", ") > -1) {
			// ip.lastIndexOf(", ");
			ip = ip.substring(ip.lastIndexOf(", ") + 2);
		}
		System.out.println(ip);
	}
}