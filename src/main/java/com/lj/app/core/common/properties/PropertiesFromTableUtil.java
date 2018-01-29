package com.lj.app.core.common.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.base.entity.UpmConfiguration;
import com.lj.app.core.common.base.service.UpmConfigurationService;
import com.lj.app.core.common.util.SpringContextHolder;

/**
 * 
 * 从数据库里取资源并覆盖
 */
public class PropertiesFromTableUtil {

  private static Log logger = LogFactory.getLog(PropertiesFromTableUtil.class);
  
  static {
    try {
      reloadConfigPro();
    } catch (Exception e) {
      logger.error(e);
    }
  }

  public static String getProperty(String key) {
    return PropertiesReader.getProperty(key);
  }

  /**
   * 重新加载配置
   * 
   * @throws Exception
   *           异常信息
   */
  public static void reloadConfigPro() throws Exception {
    UpmConfigurationService<UpmConfiguration> upmConfigurationService = SpringContextHolder
        .getBean("upmConfigurationService");
    upmConfigurationService.reloadConfigPro();
  }
}
