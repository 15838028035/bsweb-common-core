package com.lj.app.core.common.api;

import com.lj.app.core.common.security.CMSecurityContext;

public interface SecurityService {

  public CMSecurityContext getSecurityContext(int userId, String contextPath, String appId);

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @return String 返回登陆账号
   */
  public String tokenValidate(String token);

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @param appCode
   *          应用标识
   * @return String 返回登陆账号
   */
  public String tokenValidate(String token, String appCode);
}
