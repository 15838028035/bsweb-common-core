package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class ParameterNullException extends CoreBaseRunTimeException {
    
    public ParameterNullException() {
        super();
    }
    public ParameterNullException(String message) {
        super(message);
    }
    public ParameterNullException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
    public ParameterNullException(Throwable rootCause) {
        super(rootCause);
    }
}
