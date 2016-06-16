package com.lj.app.core.common.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertyUtilTest {

	private static final  String DEFAULT_ENV_NAME = "env.properties";
	
	@Test
	public void loadPropertiesTest() throws Exception{
		Properties properties = PropertyUtil.loadProperties(DEFAULT_ENV_NAME);
		assertNotNull(properties);
	}
	
	@Test(expected = java.lang.AssertionError.class)
	public void loadPropertiesNotExistsTest() throws Exception{
		Properties properties = PropertyUtil.loadProperties("notExits.properties");
		assertNull(properties);
	}
	
	@Test
	public void PropertyUtilConstructTest() {
		PropertyUtil PropertyUtil = new PropertyUtil();
		assertNotNull(PropertyUtil);
	}
	

}
