package com.lj.app.core.common.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LoginActionApiTest {

	@Test
	public void getLoginUserInfoTest() {
		LoginUserInfo LoginUserInfo = LoginActionApi.getLoginUserInfo("admin","123456");
		assertNotNull(LoginUserInfo);
	}

}
