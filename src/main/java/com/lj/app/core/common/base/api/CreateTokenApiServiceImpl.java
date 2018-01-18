package com.lj.app.core.common.base.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.CreateTokenService;

@Service
public class CreateTokenApiServiceImpl implements CreateTokenApiService {

  @Autowired
  private CreateTokenService createTokeService;

  public String CreateToken(String acctSeq, String resEntityId, int mainAcctId) throws Exception {
    return createTokeService.CreateToken(acctSeq, resEntityId, mainAcctId);
  }
}
