package com.lj.app.core.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DevDebugUtilTest {

	private DevDebugUtil devDebugUtil;
	
	@Before
	public void setUp(){
		 devDebugUtil = new DevDebugUtil();
	}
	
	@Test
	public void isDevModeTest() {
		assertTrue(DevDebugUtil.isDevModel());
	}

	@Test
	public void isProModelTest() {
		assertFalse(DevDebugUtil.isProModel());
	}
}
