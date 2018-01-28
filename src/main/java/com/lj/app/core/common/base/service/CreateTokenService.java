package com.lj.app.core.common.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.api.TokenApiService;
import com.lj.app.core.common.base.entity.UpmToken;
import com.lj.app.core.common.exception.InterfaceException;
import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 
 * 创建Token服务
 *
 */
@Service("createTokenService")
public class CreateTokenService {

  private static Log logger = LogFactory.getLog(CreateTokenService.class);

  @Autowired
  private TokenApiService tokenApiService;

  public static final  Integer TOKEN_TIME_OUT = PropertiesUtil.getInt("TOKEN_TIME_OUT",120000);

  /**
   * 创建Token
   * @param acctSeq 帐号ID
   * @param resEntityId appId
   * @param mainAcctId 帐号Id
   * @return token
   * @throws Exception 异常
   */
  public String createToken(String acctSeq, String resEntityId, int mainAcctId) throws InterfaceException,Exception {
    long lNow = System.currentTimeMillis();// 取到毫秒，但是取的是主机的时间
    String tokenId = acctSeq + "-" + lNow;
    logger.warn("==tokenId==" + tokenId);

    UpmToken upmToken = new UpmToken();
    upmToken.setTokenId(tokenId);
    if (resEntityId != null) {
      upmToken.setResId(resEntityId);
    }
    upmToken.setSubAcctId(acctSeq);
    upmToken.setMainAcctId(mainAcctId);
    String clientIp = "";
    upmToken.setClientIp(clientIp);
    upmToken.setCreateTime(new Date());

    Map<String, Object> filterMap = new HashMap<String, Object>();
    filterMap.put("subAcctId", acctSeq);
    filterMap.put("resId", resEntityId);
    filterMap.put("mainAcctId", mainAcctId);
    filterMap.put("sortColumns", "createTime desc");

    List<UpmToken> upmTokenList = tokenApiService.queryForList(filterMap);

    if (upmTokenList != null && upmTokenList.size() > 0) {
      UpmToken upmTokenQuery = (UpmToken) upmTokenList.get(0);
      Date createTime = upmTokenQuery.getCreateTime();
      long lcreateTime = createTime.getTime();
      if ((lNow - lcreateTime) > TOKEN_TIME_OUT) {
        // 账号信息查看token是否失效
        tokenApiService.saveToken(upmToken);
      } else {
        tokenId = upmTokenQuery.getTokenId();
      }
    } else {
      // 账号信息查看token是否失效
      tokenApiService.saveToken(upmToken);
    }

    return tokenId;
  }
}