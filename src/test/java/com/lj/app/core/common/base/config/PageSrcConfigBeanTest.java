package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PageSrcConfigBeanTest {

	private PageSrcConfigBean pageSrcConfigBean;
	
	@Before
	public void setUp() {
		pageSrcConfigBean = new PageSrcConfigBean();
	}
	
	@Test
	public void setCodeTest() {
		pageSrcConfigBean.setCode("code");
		assertEquals("code",pageSrcConfigBean.getCode());
	}

	@Test
	public void setTokenTest() {
		pageSrcConfigBean.setToken("token");
		assertEquals("token",pageSrcConfigBean.getToken());
	}

	@Test
	public void setFlagTest() {
		pageSrcConfigBean.setFlag("flag");
		assertEquals("flag",pageSrcConfigBean.getFlag());
	}

	@Test
	public void setLinkTitleTest() {
		pageSrcConfigBean.setLinkTitle("linkTitle");
		assertEquals("linkTitle",pageSrcConfigBean.getLinkTitle());
	}

	@Test
	public void setLinkUrlTest() {
		pageSrcConfigBean.setLinkUrl("linkUrl");
		assertEquals("linkUrl",pageSrcConfigBean.getLinkUrl());
	}

}
