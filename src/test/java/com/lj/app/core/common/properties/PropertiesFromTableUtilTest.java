package com.lj.app.core.common.properties;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class PropertiesFromTableUtilTest extends AbstractBaseSpringTransactionTestCase {

	@Test
	public void getPropertyTest() throws Exception {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesFromTableUtil.getProperty("key"));
	}

	@Test
	public void reloadConfigProTest() throws Exception {
		PropertiesFromTableUtil.reloadConfigPro();
	}

}
