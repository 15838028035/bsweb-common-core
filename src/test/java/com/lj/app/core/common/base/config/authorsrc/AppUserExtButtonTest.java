package com.lj.app.core.common.base.config.authorsrc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppUserExtButtonTest {

	private AppUserExtButton appUserExtButton;
	
	@Before
	public void setUp() {
		appUserExtButton = new AppUserExtButton();
	}
	
	@Test
	public void setKeyTes() {
		appUserExtButton.setKey("key");
		assertEquals("key",appUserExtButton.getKey());
	}

	@Test
	public void setNameTest() {
		appUserExtButton.setName("setName");
		assertEquals("setName",appUserExtButton.getName());
	}

	@Test
	public void setCodeTest() {
		appUserExtButton.setCode("setCode");
		assertEquals("setCode",appUserExtButton.getCode());
	}

	@Test
	public void setUrlTest() {
		appUserExtButton.setUrl("setUrl");
		assertEquals("setUrl",appUserExtButton.getUrl());
	}

	@Test
	public void setMulticountTest() {
		appUserExtButton.setMulticount("setMulticount");
		assertEquals("setMulticount",appUserExtButton.getMulticount());
	}

	@Test
	public void setBeforeopenTest() {
		appUserExtButton.setBeforeopen("setBeforeopen");
		assertEquals("setBeforeopen",appUserExtButton.getBeforeopen());
	}

}
