package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SubAppConfigBeanTest {

	private SubAppConfigBean subAppConfigBean;
	
	@Before
	public void setUp() {
		subAppConfigBean = new SubAppConfigBean();
	}
	
	@Test
	public void setCodeTest() {
		subAppConfigBean.setCode("code");
		assertEquals("code",subAppConfigBean.getCode());
	}

	@Test
	public void setAppNameTest() {
		subAppConfigBean.setAppName("appName");
		assertEquals("appName",subAppConfigBean.getAppName());
	}

	@Test
	public void setSsoUrl() {
		subAppConfigBean.setSsoUrl("ssoUrl");
		assertEquals("ssoUrl",subAppConfigBean.getSsoUrl());
	}

}
