package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class AccountMailException  extends CoreBaseRunTimeException {

	public AccountMailException() {
		super();
	}

	public AccountMailException(String message) {
		super(message);
	}

	public AccountMailException(Throwable cause) {
		super(cause);
	}

	public AccountMailException(String message, Throwable cause) {
		super(message, cause);
	}

}
