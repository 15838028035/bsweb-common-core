package com.lj.app.core.common.security;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class EncryptDSPropertiesFactory {

  private static final String USER = "user";
  private static final String PASSWORD = "password";
  private static Log logger = LogFactory.getLog(EncryptDSPropertiesFactory.class);

  private static Map<String, String> passMap = new ConcurrentHashMap<String, String>();
  /**
   * 存放的是解密后的密码
   */
  private static String originalPassword = null;

  private EncryptDSPropertiesFactory() {

  }

  public synchronized static Properties getProperties(Boolean isPwdEncryped, String username, String password)
      throws Exception {
    Properties p = new Properties();
    p.setProperty(USER, username);

    /**
     * 如果密码是加密的，那么进行解密。如果还没有解密，那么久进行解密后设置设置originalPassword=解密后的密码； 否则如果密码没有加密，就设置originalPassword=传进来的密码
     */
    if (isPwdEncryped) {
      originalPassword = DesUtil.decrypt(password);
    } else {
      originalPassword = password;
    }
    p.setProperty(PASSWORD, originalPassword);
    return p;
  }

  /**
   * 
   * @param s
   *          encrypted password
   * @return
   */
  public static String getDecodedPass(String s) throws Exception {
    if (s == null) {
      return null;
    }
    String pass = passMap.get(s);
    if (pass == null) {
      logger.debug("now decode the password...");
      pass = DesUtil.decrypt(pass);
      passMap.put(s, pass);
    }
    return pass;
  }

}