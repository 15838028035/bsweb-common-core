package com.lj.app.core.common.security;

import org.junit.Test;

public class DesUtilTest {


	@Test
	public void sysadminEncryptTest() throws Exception{
		String sysadmin = DesUtil.encrypt("123456");
		System.out.println("sysadmin=" +sysadmin);
	}
}
