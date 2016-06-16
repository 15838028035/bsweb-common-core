package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class LoginUserPasswordErrorException extends CoreBaseRunTimeException {
    
    public LoginUserPasswordErrorException() {
        super();
    }
    public LoginUserPasswordErrorException(String message) {
        super(message);
    }
    public LoginUserPasswordErrorException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
    public LoginUserPasswordErrorException(Throwable rootCause) {
        super(rootCause);
    }
}

