package com.lj.app.core.common.base.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.CreateTokenService;

/**
 * 
 * token服务
 *
 */
@Service
public class CreateTokenApiServiceImpl implements CreateTokenApiService {

  @Autowired
  private CreateTokenService createTokeService;

  public String createToken(String acctSeq, String resEntityId, int mainAcctId) throws Exception {
    return createTokeService.createToken(acctSeq, resEntityId, mainAcctId);
  }
}
