package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SoapUrlConfigBeanTest {

	private SoapUrlConfigBean soapUrlConfigBean;
	
	@Before
	public void setUp() {
		soapUrlConfigBean = new SoapUrlConfigBean();
	}
	
	@Test
	public void setNameTest() {
		soapUrlConfigBean.setName("name");
		assertEquals("name",soapUrlConfigBean.getName());
	}

	@Test
	public void setAuthenTest() {
		soapUrlConfigBean.setAuthen("authen");
		assertEquals("authen",soapUrlConfigBean.getAuthen());
	}

	@Test
	public void setClientIpTest() {
		soapUrlConfigBean.setClientIp("clientIp");
		assertEquals("clientIp",soapUrlConfigBean.getClientIp());
	}

	@Test
	public void setUrlAddrTest() {
		soapUrlConfigBean.setUrlAddr("urlAddr");
		assertEquals("urlAddr",soapUrlConfigBean.getUrlAddr());
	}

}
