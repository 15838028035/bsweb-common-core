package com.lj.app.core.common.security;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SecurityConstantsTest {

	private SecurityConstants securityConstants;
	
	@Before
	public void setUp() {
		securityConstants = new SecurityConstants();
	}
	
	@Test
	public void indexTest() {
		assertEquals("index",SecurityConstants.INDEX);
	}
	
	@Test
	public void loginTest() {
		assertEquals("login",SecurityConstants.LOGIN);
	}
	
	@Test
	public void noPermissionTest() {
		assertEquals("noPermission",SecurityConstants.NOPERMISSION);
	}
	
	@Test
	public void securityContextTest() {
		assertEquals("securityContext",SecurityConstants.SECURITY_CONTEXT);
	}
	
	@Test
	public void syserrorTest() {
		assertEquals("syserror",SecurityConstants.SYSERROR);
	}
	
	@Test
	public void privilegeMenuTest() {
		assertEquals(1,SecurityConstants.PRIVILEGE_MENU);
	}
	
	@Test
	public void privilegeButtonTest() {
		assertEquals(2,SecurityConstants.PRIVILEGE_BUTTON);
	}

}
