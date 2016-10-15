package com.lj.app.core.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.properties.PropertiesUtil;

public class DevDebugUtilTest {

	private DevDebugUtil devDebugUtil;
	
	@Before
	public void setUp(){
		 devDebugUtil = new DevDebugUtil();
	}
	
	@Test
	public void isDevModeTest() {
		PropertiesUtil.setProperty("devMode","dev");
		assertTrue(DevDebugUtil.isDevModel());
	}

	@Test
	public void isProModelTest() {
		PropertiesUtil.setProperty("devMode","dev");
		assertFalse(DevDebugUtil.isProModel());
	}
}
