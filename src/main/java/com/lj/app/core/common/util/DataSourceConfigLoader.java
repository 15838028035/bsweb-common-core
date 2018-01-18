package com.lj.app.core.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.security.DesUtil;

/**
 * 
 * 自定义数据源加载工具类
 *
 */
public class DataSourceConfigLoader {
  private static Log logger = LogFactory.getLog(DataSourceConfigLoader.class);

  /**
   * 获得属性的值
   * @param decode 是否解密
   * @param key 属性key
   * @return 获得属性的值
   * @throws Exception 异常信息
   */
  public static synchronized String getPropertiesConfig(Boolean decode, String key) throws Exception {
    String value = PropertiesUtil.getProperty(key);
    logger.debug(key + "======" + value);
    if (StringUtil.isNotBlank(value) && decode.booleanValue()) {
      value = DesUtil.decrypt(value);
    }
    return value;
  }
}