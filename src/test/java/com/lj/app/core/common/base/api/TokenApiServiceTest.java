package com.lj.app.core.common.base.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.entity.UpmToken;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class TokenApiServiceTest extends AbstractBaseSpringTransactionTestCase{

	@Autowired
	private TokenApiService tokenApiService;
	
	@Test
	public void saveTokenTest()  throws Exception {
		
		tokenApiService.delete(1);
		
		UpmToken upmToken = new UpmToken();
		upmToken.setTokenId("tokenId");
		upmToken.setId(1);
		
		tokenApiService.saveToken(upmToken);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tokenId","tokenId");
		
		List list = tokenApiService.queryForList(map);
		
		assertNotNull(list);
		assertTrue(list.size()>0);
		
		tokenApiService.delete(1);
	}

}
