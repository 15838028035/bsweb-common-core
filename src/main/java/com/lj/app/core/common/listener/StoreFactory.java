package com.lj.app.core.common.listener;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * @title :MemStat.java
 * @description 依托内存实现在线用户统计功能
 */
public class StoreFactory {

	private static final String STAT_STORE = "statStore";
	private static final String DATABASE = "db";
	private static final String MEMORY = "memory";

	/**
	 * @description 返回实现IStatStore接口的某种对象
	 * @param
	 * @return
	 */
	public static IStatStore getStore() {
		String statStore = PropertiesUtil.getProperty(STAT_STORE);
		IStatStore iStatStore = null;
		iStatStore = new MemStat();
		return iStatStore;
	}
}
