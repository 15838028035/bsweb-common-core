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
import com.lj.app.core.common.base.model.UpmToken;
import com.lj.app.core.common.properties.PropertiesUtil;

@Service("createTokenService")
public class CreateTokenService {

	private static Log logger =  LogFactory.getLog(CreateTokenService.class);
	
	@Autowired 
	private TokenApiService tokenApiService;
	
	private final static String TOKEN_TIME_OUT = PropertiesUtil.getProperty("TOKEN_TIME_OUT");
	
	public String CreateToken(String acctSeq,String resEntityId,int mainAcctId) throws Exception
	{
		long lNow = System.currentTimeMillis();//取到毫秒，但是取的是主机的时间
		String tokenId =  acctSeq + "-" + lNow;
		//String tokenId =  EncryptInterface.desEncryptData(acctSeq + "-" + lNow);
		//long lNow = System.currentTimeMillis() - 1291710000000L;//临时测试用
		logger.warn("==tokenId=="+tokenId);
		
		//create token and save it
		UpmToken upmToken = new UpmToken();
		upmToken.setTokenId(tokenId);
		if(resEntityId != null)
			upmToken.setResId(resEntityId);
		upmToken.setSubAcctId(acctSeq);
		upmToken.setMainAcctId(mainAcctId);
		String clientIp = "";
		upmToken.setClientIp(clientIp);
		upmToken.setCreateTime(new Date());
		
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("subAcctId", acctSeq);
		filterMap.put("resId", resEntityId);
		filterMap.put("mainAcctId", mainAcctId);
		filterMap.put("sortColumns", "createTime desc");
		
		List<UpmToken> upmTokenList = tokenApiService.queryForList(filterMap);
		
		if(upmTokenList!=null&& upmTokenList.size()>0){
			UpmToken upmTokenQuery =  (UpmToken)upmTokenList.get(0);
			Date createTime = upmTokenQuery.getCreateTime();
			long lcreateTime = createTime.getTime();
			if((lNow - lcreateTime) > Integer.parseInt(TOKEN_TIME_OUT)*1000){
				//账号信息查看token是否失效
				tokenApiService.saveToken(upmToken);
			}else {
				tokenId = upmTokenQuery.getTokenId();
			}
		}else {
			//账号信息查看token是否失效
			tokenApiService.saveToken(upmToken);
		}
		
		return tokenId;
	}
}