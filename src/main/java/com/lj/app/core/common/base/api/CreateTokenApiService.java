package com.lj.app.core.common.base.api;

import com.lj.app.core.common.exception.InterfaceException;

/**
 * 
 * token服务
 *
 */
public interface CreateTokenApiService {

  /**
   * 统一TOKEN创建服务
   * 
   * @param acctSeq
   *          帐号ID
   * @param resEntityId
   *          资源实体ID
   * @param mainAcctId
   *          主帐号ID
   * @return TOKEN
   * @throws Exception 异常
   */
  public String createToken(String acctSeq, String resEntityId, int mainAcctId) throws InterfaceException,Exception;
}
