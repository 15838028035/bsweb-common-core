package com.lj.app.core.common.exception;

import org.junit.Test;

public class SslLoginUserNotExistsExceptionTest {

	@Test(expected = SslLoginUserNotExistsException.class)
	public void sslLoginUserNotExistsExceptionTest() throws Exception{
		throw new SslLoginUserNotExistsException();
	}

	@Test(expected = SslLoginUserNotExistsException.class)
	public void sslLoginUserNotExistsExceptionMsgTest() throws Exception{
		throw new SslLoginUserNotExistsException("SslLoginUserNotExistsException");
	}

	@Test(expected = SslLoginUserNotExistsException.class)
	public void sslLoginUserNotExistsExceptionThrowTest()  throws Exception{
		Throwable cause = new Throwable();
		throw new SslLoginUserNotExistsException(cause);
	}

	@Test(expected = SslLoginUserNotExistsException.class)
	public void sslLoginUserNotExistsExceptionThrowMsgTest() throws Exception{
		Throwable cause = new Throwable();
		throw new SslLoginUserNotExistsException("SslLoginUserNotExistsException",cause);
	}
}
