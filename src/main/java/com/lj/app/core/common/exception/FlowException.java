package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class FlowException extends CoreBaseRunTimeException {

	public FlowException() {
		super();
	}
	public FlowException(String e) {
		super(e);
	}
	public FlowException(Throwable cause) {
		super(cause);
	}
	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}
}
