package com.lj.app.core.common.exception;

import org.junit.Test;

public class SsoLoginTokenValidExceptionTest {

	@Test(expected = SsoLoginTokenValidException.class)
	public void ssoLoginTokenValidExceptionTest() throws Exception{
		throw new SsoLoginTokenValidException();
	}

	@Test(expected = SsoLoginTokenValidException.class)
	public void ssoLoginTokenValidExceptionMsgTest() throws Exception{
		throw new SsoLoginTokenValidException("SslLoginUserNotExistsException");
	}

	@Test(expected = SsoLoginTokenValidException.class)
	public void ssoLoginTokenValidExceptionThrowTest()  throws Exception{
		Throwable cause = new Throwable();
		throw new SsoLoginTokenValidException(cause);
	}

	@Test(expected = SsoLoginTokenValidException.class)
	public void ssoLoginTokenValidExceptionThrowMsgTest() throws Exception{
		Throwable cause = new Throwable();
		throw new SsoLoginTokenValidException("SslLoginUserNotExistsException",cause);
	}

}
