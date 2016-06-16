package com.lj.app.core.common.base.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class CreateTokenServiceTest extends AbstractBaseSpringTransactionTestCase{
	
	@Autowired
	private CreateTokenService createTokenService;

	@Test
	public void createTokenTest() throws Exception{
		String tokenId = createTokenService.CreateToken("1", "UPM", 0);
		assertNotNull(tokenId);
	}

}
