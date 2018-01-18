package com.lj.app.core.common.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.entity.UpmToken;
import com.lj.app.core.common.base.entity.UpmUser;
import com.lj.app.core.common.base.service.UpmTokenService;
import com.lj.app.core.common.base.service.UpmUserService;
import com.lj.app.core.common.security.CMSecurityContext;

@Service
public class SecurityServiceImpl implements SecurityService {

  @Autowired
  public UpmTokenService upmTokenService;

  @Autowired
  public UpmUserService upmUserService;

  public CMSecurityContext getSecurityContext(int userId, String contextPath, String appId) {
    return UpmPermissionActionApi.getSecurityContext(userId, contextPath, appId);
  }

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @return String 返回登陆账号
   */
  public String tokenValidate(String token) {
    Integer mainAcctId = 0;
    Map<String, String> filterMap = new HashMap<String, String>();
    filterMap.put("tokenId", token);

    List<UpmToken> list = upmTokenService.queryForList("select", filterMap);
    if (list != null && list.size() > 0) {
      mainAcctId = list.get(0).getMainAcctId();
    }

    Map<String, Integer> userFilterMap = new HashMap<String, Integer>();
    userFilterMap.put("id", mainAcctId);

    List<UpmUser> upmUserList = upmUserService.queryForList("select", userFilterMap);

    if (upmUserList != null && upmUserList.size() > 0) {
      return upmUserList.get(0).getLoginNo();
    }
    return null;
  }

  /**
   * token验证
   * 
   * @param token
   *          令牌
   * @param appCode
   *          应用标识
   * @return String 返回登陆账号
   */
  public String tokenValidate(String token, String appCode) {
    Integer mainAcctId = 0;
    Map<String, String> filterMap = new HashMap<String, String>();
    filterMap.put("tokenId", token);

    List<UpmToken> list = upmTokenService.queryForList("select", filterMap);
    if (list != null && list.size() > 0) {
      mainAcctId = list.get(0).getMainAcctId();
    }

    Map<String, Integer> userFilterMap = new HashMap<String, Integer>();
    userFilterMap.put("id", mainAcctId);

    List<UpmUser> upmUserList = upmUserService.queryForList("select", userFilterMap);

    if (upmUserList != null && upmUserList.size() > 0) {
      return upmUserList.get(0).getLoginNo();
    }
    return null;
  }
}
