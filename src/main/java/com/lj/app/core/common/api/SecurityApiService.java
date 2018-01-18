package com.lj.app.core.common.api;

import com.lj.app.core.common.security.CMSecurityContext;

/**
 * 
 * 权限服务Api
 *
 */
public interface SecurityApiService {
  public CMSecurityContext getSecurityContext(int userId, String contextPath, String appId);

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @return 返回主帐号名字
   */
  public String tokenValidate(String token);

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @param appCode
   *          应用标识
   * @return 返回主帐号名字
   */
  public String tokenValidate(String token, String appCode);

}
