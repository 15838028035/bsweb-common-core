package com.lj.app.core.common.properties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.lj.app.core.common.util.StringUtil;


public class PropertiesReader {
	private static Log logger = LogFactory.getLog(PropertiesReader.class);
	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	
	private static final String BASE_CONFIG_FILE_NAME = "base-config.properties";
	
	private static final String ENV_CONFIG_FILE_NAME = "env.properties";
	private static final String RULE_CONFIG_FILE_NAME = "rule.properties";
	
	private static final String CORE_URL="";
	
	private static Properties properties = new Properties();

	static {
		loadConfigFile();
	}

	private static void loadConfigFile() {
		try {
			
			logger.warn("Now,Loading default file~~~");
			loadConfigFileByUrlAndFileName(CORE_URL,ENV_CONFIG_FILE_NAME);
			loadConfigFileByUrlAndFileName(CORE_URL,RULE_CONFIG_FILE_NAME);
			logger.warn("Loading config file finished~~");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("loadConfigFileError===" + e.getMessage());
		}
	}

	private static void loadConfigFileByUrlAndFileName(String properPath, String configName) throws Exception {
		String properFile = properPath + configName;
		logger.warn("Now,Loading prov file=== " + properFile);
		Properties provProperties = PropertiesLoaderUtils.loadAllProperties(properFile, PropertiesReader.class.getClassLoader());
		properties.putAll(provProperties);
	}

	public static String getBaseConfigValue(String key) {
		return getBaseConfigValue(key, null);
	}

	public static String getValue(String properName, String key,
			String defalutValue) {
			String value = getValue(properName, key);
			return (value == null || value.equals("")) ? defalutValue : value;
	}
	
	public static String getBaseConfigValue(String key, String defalutValue) {
		return getValue(BASE_CONFIG_FILE_NAME, key, defalutValue);
	}

	public static String getValue(String properName, String key) {
		try {
			Properties properties = propertiesMap.get(properName);
			if (properties != null) {
				logger.debug("ReadConfig[properName=" + properName + ",key="
						+ key + ",value=" + properties.getProperty(key) + "]");
				return properties.getProperty(key);
			}
			properties = PropertiesLoaderUtils.loadAllProperties(properName,PropertiesReader.class.getClassLoader());
			propertiesMap.put(properName, properties);
			logger.debug("ReadConfig[properName=" + properName + ",key=" + key
					+ ",value=" + properties.getProperty(key) + "]");
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Properties getProperties() {
		return properties;
	}
	
	public static String getValueAndDefault(String key, String defaultValue) {
		String value = properties.getProperty(key, defaultValue);
		logger.debug("ReadConfig[key=" + key + ",value=" + value + "]");
		return value;
	}
	
	public static Object setProperty(String key,int value) {
	return setProperty(key, String.valueOf(value));
	}

	public static Object setProperty(String key,long value) {
	    return setProperty(key, String.valueOf(value));
	}

	public static Object setProperty(String key,float value) {
	    return setProperty(key, String.valueOf(value));
	}

	public static  Object setProperty(String key,double value) {
	    return setProperty(key, String.valueOf(value));
	}

	public static  Object setProperty(String key,boolean value) {
	    return setProperty(key, String.valueOf(value));
	}

	public static boolean contains(Object value) {
		return properties.contains(value);
	}

	public static boolean containsKey(Object key) {
	    return properties.containsKey(key);
	}

	public static  boolean containsValue(Object value) {
	    return properties.containsValue(value);
	}

	public static  Integer getInteger(String key) {
		String value = getProperty(key);
		if(StringUtil.isBlank(value)) {
		return null;
		}
		return Integer.parseInt(value);
	}

	public  static int getInt(String key,int defaultValue) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return defaultValue;
	    }
	    return Integer.parseInt(value);
	}

	/**
	 * 必须存在这个key的值,不然抛 IllegalStateException异常
	 **/
	public static int getRequiredInt(String key) throws IllegalStateException {
	    return Integer.parseInt(getRequiredProperty(key));
	}

	public static Long getLong(String key) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return null;
	    }
	    return Long.parseLong(value);
	}

	public static  long getLong(String key,long defaultValue) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return defaultValue;
	    }
	    return Long.parseLong(value);
	}

	/**
	 * 必须存在这个key的值,不然抛 IllegalStateException异常
	 **/
	public  static long getRequiredLong(String key) throws IllegalStateException {
	    return Long.parseLong(getRequiredProperty(key));
	}

	public static Boolean getBoolean(String key) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return null;
	    }
	    return Boolean.parseBoolean(value);
	}

	public static boolean getBoolean(String key,boolean defaultValue) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return defaultValue;
	    }
	    return Boolean.parseBoolean(value);
	}

	/**
	 * 必须存在这个key的值,不然抛 IllegalStateException异常
	 **/
	public static  boolean getRequiredBoolean(String key) throws IllegalStateException {
	    return Boolean.parseBoolean(getRequiredProperty(key));
	}

	public static Float getFloat(String key) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return null;
	    }
	    return Float.parseFloat(value);
	}

	public static float getFloat(String key,float defaultValue) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return defaultValue;
	    }
	    return Float.parseFloat(value);
	}

	/**
	 * 必须存在这个key的值,不然抛 IllegalStateException异常
	 **/    
	public static  float getRequiredFloat(String key) throws IllegalStateException {
	    return Float.parseFloat(getRequiredProperty(key));
	}

	public static Double getDouble(String key) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return null;
	    }
	    return Double.parseDouble(value);
	}

	public static double getDouble(String key,double defaultValue) {
	    String value = getProperty(key);
	    if(StringUtil.isBlank(value)) {
	        return defaultValue;
	    }
	    return Double.parseDouble(value);
	}

	/**
	 * 必须存在这个key的值,不然抛 IllegalStateException异常
	 **/
	public  static double getRequiredDouble(String key) throws IllegalStateException {
	    return Double.parseDouble(getRequiredProperty(key));
	}
	
	public static Object setProperty(String key,String value) {
		return properties.setProperty(key, value);
	}
	
	public  static String getProperty(String key, String defaultValue) {
		String value = getProperty(key);
		if(StringUtil.isBlank(value)) {
		return defaultValue;
		}
		return value;
	}
	
	public  static String getProperty(String key) {
		String value = properties.getProperty(key);
		logger.debug("ReadConfig[key=" + key + ",value=" + value + "]");
		return value;
	}
	
	public static void clear() {
		properties.clear();
	}
	
	/**
	* 必须存在这个key的值,不然抛 IllegalStateException异常
	**/
	public static String getRequiredProperty(String key) throws IllegalStateException {
		String value = getProperty(key);
		if(StringUtil.isBlank(value)) {
		throw new IllegalStateException("required property is blank by key="+key);
		}
		return value;
	}

}
