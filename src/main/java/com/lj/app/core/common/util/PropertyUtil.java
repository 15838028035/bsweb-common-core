package com.lj.app.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties Util函数.
 * 
 */
public class PropertyUtil {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger logger = Logger.getLogger(PropertyUtil.class);

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	private static Properties properties;
	
	private static final  String DEFAULT_ENV_NAME = "env.properties";
	
	private static  Resource resource;
	
	
	static {
		try{
			properties = PropertyUtil.loadProperties(DEFAULT_ENV_NAME);
		}catch(Exception e) {
			logger.error("Error load env.properties");
		}
	}
	
	/**
	 * 载入多个ClassPath中的properties文件, 相同的属性将会覆盖之前的载入.
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... locations) throws IOException {
		Properties props = new Properties();

		for (String location : locations) {

			logger.debug("Loading properties file from classpath:" + location);

			InputStream is = null;
			try {
				 setClassPathResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}
	
	
	public static void setClassPathResource(String location){
			resource = new ClassPathResource(location);
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		PropertyUtil.properties = properties;
	}
	
}
