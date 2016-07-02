package com.lj.app.core.common.base.config.authorsrc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppAuthorOptionOperateTest {

	private AppAuthorOptionOperate appAuthorOptionOperate;
	
	@Before
	public void setUp() {
		appAuthorOptionOperate = new AppAuthorOptionOperate();
	}

	@Test
	public void setCodeTest() {
		appAuthorOptionOperate.setCode("code");
		assertEquals("code",appAuthorOptionOperate.getCode());
	}

	@Test
	public void setNameTest() {
		appAuthorOptionOperate.setName("setName");
		assertEquals("setName",appAuthorOptionOperate.getName());
	}
	
	@Test
	public void setUrltypeTest() {
		appAuthorOptionOperate.setUrltype("setUrltype");
		assertEquals("setUrltype",appAuthorOptionOperate.getUrltype());
	}

	@Test
	public void setUrlTest() {
		appAuthorOptionOperate.setUrl("setUrl");
		assertEquals("setUrl",appAuthorOptionOperate.getUrl());
	}

	@Test
	public void setTypeTest() {
		appAuthorOptionOperate.setType("setType");
		assertEquals("setType",appAuthorOptionOperate.getType());
	}

	@Test
	public void setIsBatchTest() {
		appAuthorOptionOperate.setIsBatch("setIsBatch");
		assertEquals("setIsBatch",appAuthorOptionOperate.getIsBatch());
	}

	@Test
	public void setBatchDescTest() {
		appAuthorOptionOperate.setBatchDesc("setBatchDesc");
		assertEquals("setBatchDesc",appAuthorOptionOperate.getBatchDesc());
	}

	@Test
	public void setBatchTemplatePathTest() {
		appAuthorOptionOperate.setBatchTemplatePath("setBatchTemplatePath");
		assertEquals("setBatchTemplatePath",appAuthorOptionOperate.getBatchTemplatePath());
	}

}
