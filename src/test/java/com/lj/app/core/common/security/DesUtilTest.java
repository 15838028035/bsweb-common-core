package com.lj.app.core.common.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DesUtilTest {

	@Test
	public void dncryptTest() throws Exception {
		
		String  enData= DesUtil.encrypt("data");
		String data = DesUtil.decrypt(enData);
		
		assertEquals("data",data);
	}
	
	
	@Test
	public void decryptTest() throws Exception {
		String  data= DesUtil.decrypt(null);
		assertNull(data);
	}

}
