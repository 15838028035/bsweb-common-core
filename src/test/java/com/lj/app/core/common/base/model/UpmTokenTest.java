package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UpmTokenTest {

	private UpmToken upmToken;
	
	@Before
	public void SetUp() {
		upmToken  = new UpmToken();
	}
	
	@Test
	public void setIdTest() {
		upmToken.setId(1);
		assertTrue(1==upmToken.getId());
	}

	@Test
	public void setTokenIdTest() {
		upmToken.setTokenId("tokenId");
		assertEquals("tokenId",upmToken.getTokenId());
	}

	@Test
	public void setResIdTest() {
		upmToken.setResId("resId");
		assertEquals("resId",upmToken.getResId());
	}

	@Test
	public void setSubAcctIdTest() {
		upmToken.setSubAcctId("subAcctId");
		assertEquals("subAcctId",upmToken.getSubAcctId());
	}

	@Test
	public void setClientIpTest() {
		upmToken.setClientIp("clientIp");
		assertEquals("clientIp",upmToken.getClientIp());
	}

	@Test
	public void setCreateTimeTest() {
		upmToken.setCreateTime(new Date());
		assertNotNull(upmToken.getCreateTime());
	}

	@Test
	public void setMainAcctIdTest() {
		upmToken.setMainAcctId(1);
		assertTrue(1==upmToken.getMainAcctId());
	}

}
