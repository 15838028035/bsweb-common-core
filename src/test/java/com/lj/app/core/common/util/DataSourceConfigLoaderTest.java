package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataSourceConfigLoaderTest {

	@Test
	public void getPropertiesConfigTest() throws Exception {
		assertEquals("dev",DataSourceConfigLoader.getPropertiesConfig(false, "devMode"));
		assertEquals(null,DataSourceConfigLoader.getPropertiesConfig(false, "DataSourceKeyNotFound"));
		assertEquals("sysadmin",DataSourceConfigLoader.getPropertiesConfig(true, "password"));
		assertEquals(null,DataSourceConfigLoader.getPropertiesConfig(true, "DataSourceKeyNotFound"));
	}
	
}
