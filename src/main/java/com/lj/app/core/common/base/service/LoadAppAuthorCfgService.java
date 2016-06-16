package com.lj.app.core.common.base.service;

import com.lj.app.core.common.base.config.authorsrc.AppAuthorConfigBean;
import com.lj.app.core.common.base.config.authorsrc.AppAuthorOption;

public interface LoadAppAuthorCfgService {

	/**
	 * 获取应用的授权配置
	 * 
	 * @param appCode
	 *            应用编码
	 * @return
	 */
	public AppAuthorConfigBean getAppAuthorConfig(String appCode);

	/**
	 * 获取授权实体的前台配置
	 * 
	 * @param appCode
	 *            应用编码
	 * @param opType
	 *            授权实体的类型
	 * @return
	 */
	public AppAuthorOption getAppAuthorOption(String appCode, String optype);

}
