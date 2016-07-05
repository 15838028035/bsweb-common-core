package com.lj.app.core.common.util;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 
 *开发调试工具类
 */
public class DevDebugUtil {

	/**
	 * 是否是开发模式
	 * 根据配置文件rule.properties中的
	 * devMode = dev  开发模式
	 * devModel = pro 产品模式
	 * 
	 * @return
	 */
	public static boolean isDevModel() {
		String devModel = PropertiesUtil.getProperty("devMode");
		devModel = StringUtil.trimBlank(devModel);
		return StringUtil.isEqualsIgnoreCase(devModel, "dev");
	}
	
	/**
	 * 是否是开发模式
	 * 根据配置文件rule.properties中的
	 * devMode = dev  开发模式
	 * devModel = pro 产品模式
	 * 
	 * @return
	 */
	public static boolean isProModel() {
		String devModel = PropertiesUtil.getProperty("devMode");
		devModel = StringUtil.trimBlank(devModel);
		return StringUtil.isEqualsIgnoreCase(devModel, "pro");
	}

}
