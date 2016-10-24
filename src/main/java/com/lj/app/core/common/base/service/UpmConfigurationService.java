package com.lj.app.core.common.base.service;


public interface UpmConfigurationService<UpmConfiguration> extends BaseService {
	
	/**
	 * 重新加载数据库中的配置信息
	 */
	public  void reloadConfigPro() throws Exception;
}
