package com.lj.app.core.common.base.api;

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
   * @throws Exception
   */
  public String CreateToken(String acctSeq, String resEntityId, int mainAcctId) throws Exception;
}
