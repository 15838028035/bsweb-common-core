package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.base.entity.UpmConfiguration;

public class UpmConfigurationTest {

	private UpmConfiguration upmConfiguration;
	
	@Before
	public void setUp(){
		upmConfiguration = new UpmConfiguration();
	}
	
	@Test
	public void setConfigIdTest() {
		upmConfiguration.setConfigId(1);
		assertTrue(1==upmConfiguration.getConfigId());
	}

	@Test
	public void setCfgKeyTest() {
		upmConfiguration.setCfgKey("cfgkey");
		assertEquals("cfgkey",upmConfiguration.getCfgKey());
	}

	@Test
	public void setCfgValueTest() {
		upmConfiguration.setCfgValue("cfgValue");
		assertEquals("cfgValue",upmConfiguration.getCfgValue());
	}

	@Test
	public void setCfgDescTest() {
		upmConfiguration.setCfgDesc("cfgDesc");
		assertEquals("cfgDesc",upmConfiguration.getCfgDesc());
	}

}
