package com.lj.app.core.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

public class IpAddressUtil {
	public static String getIP4(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

	/**
	 * 获取webservice客户端请求IP地址
	 * 
	 * @param isXforward
	 *            是否通过x-forwarded-for从http头获取真实ip
	 * @return 客户端请求ip
	 */
	public static String getClientIpAxis(boolean isXforward) {
		HttpServletRequest request = getAxisRequest();
		if (request == null) {
			return null;
		}
		if (isXforward) {
			return getIP4(request);
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 获取webservice客户端请求IP地址
	 * 
	 * @return 客户端请求ip
	 */
	public static String getClientIpAxis() {
		return getClientIpAxis(false);
	}

	/**
	 * 获取webservice客户端请求request对象
	 * 
	 * @return request对象
	 */
	public static HttpServletRequest getAxisRequest() {
		HttpServletRequest request = null;
		try {
			MessageContext mc = MessageContext.getCurrentContext();
			if (mc != null) {
				request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

}
