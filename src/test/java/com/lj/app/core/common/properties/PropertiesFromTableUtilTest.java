package com.lj.app.core.common.properties;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PropertiesFromTableUtilTest {

	@Test
	public void getPropertyTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesFromTableUtil.getProperty("key"));
	}

	@Test
	public void reloadConfigProTest() {
		PropertiesFromTableUtil.reloadConfigPro();
	}

}
