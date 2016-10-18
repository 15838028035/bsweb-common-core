package com.lj.app.core.common.exception;

import org.junit.Test;

public class FlowExceptionTest {
	@Test(expected = FlowException.class)
	public void flowExceptionTest() throws Exception{
		throw new FlowException();
	}

	@Test(expected = FlowException.class)
	public void flowExceptionMsgTest() throws Exception{
		throw new FlowException("FlowException");
	}

	@Test(expected = FlowException.class)
	public void flowExceptionThrowTest()  throws Exception{
		Throwable cause = new Throwable();
		throw new FlowException(cause);
	}

	@Test(expected = FlowException.class)
	public void flowExceptionThrowMsgTest() throws Exception{
		Throwable cause = new Throwable();
		throw new FlowException("FlowException",cause);
	}

}
