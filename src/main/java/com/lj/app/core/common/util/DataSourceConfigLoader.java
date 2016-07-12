package com.lj.app.core.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.security.DesUtil;

public class DataSourceConfigLoader {
	private static Log logger = LogFactory.getLog(DataSourceConfigLoader.class);

	public static synchronized String getPropertiesConfig(Boolean decode,
			String key) throws Exception{
		String value = PropertiesUtil.getProperty(key);
		logger.debug(key + "======" + value );
		if (StringUtil.isNotBlank(value) && decode.booleanValue()) {
			value = DesUtil.decrypt(value);
		}
		return value;
	}
}