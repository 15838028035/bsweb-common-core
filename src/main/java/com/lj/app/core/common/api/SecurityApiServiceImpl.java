package com.lj.app.core.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.security.CmSecurityContext;

/**
 * 
 * 权限服务Api
 *
 */
@Service("securityApiService")
public class SecurityApiServiceImpl implements SecurityApiService {
  @Autowired
  private SecurityService securityService;

  public CmSecurityContext getSecurityContext(int userId, String contextPath, String appId) {
    return securityService.getSecurityContext(userId, contextPath, appId);
  }

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @return String 登陆账号
   */
  public String tokenValidate(String token) {
    return securityService.tokenValidate(token);
  }

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @param appCode
   *          应用标识
   * @return String 登陆账号
   */
  public String tokenValidate(String token, String appCode) {
    return securityService.tokenValidate(token, appCode);
  }
}
