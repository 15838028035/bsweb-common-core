package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PermissionConfigBeanTest {

	private PermissionConfigBean permissionConfigBean;
	
	@Before
	public void setUp(){
		permissionConfigBean = new PermissionConfigBean();
	}
	
	@Test
	public void setTypeTest() {
		permissionConfigBean.setType("type");
		assertEquals("type",permissionConfigBean.getType());
	}

	@Test
	public void setRoottypeTest() {
		permissionConfigBean.setRoottype("roottype");
		assertEquals("roottype",permissionConfigBean.getRoottype());
	}

	@Test
	public void setRootvalueTest() {
		permissionConfigBean.setRootvalue("rootvalue");
		assertEquals("rootvalue",permissionConfigBean.getRootvalue());
	}

}
