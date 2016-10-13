package com.lj.app.core.common.properties;

import com.lj.app.core.common.base.entity.UpmConfiguration;
import com.lj.app.core.common.base.service.UpmConfigurationService;
import com.lj.app.core.common.util.SpringContextHolder;

/**
 * 
 * 从数据库里取资源并覆盖 
 */
public class PropertiesFromTableUtil{
	static{
		reloadConfigPro();
	}
	
	public static String getProperty(String key) {
		return PropertiesReader.getProperty(key);
	}
	
	public static void reloadConfigPro() {
		UpmConfigurationService<UpmConfiguration> upmConfigurationService = SpringContextHolder.getBean("upmConfigurationService");
		upmConfigurationService.reloadConfigPro();
	}
}
