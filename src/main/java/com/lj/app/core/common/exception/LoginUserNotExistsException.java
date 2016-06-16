package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class LoginUserNotExistsException extends CoreBaseRunTimeException {
    
    public LoginUserNotExistsException() {
        super();
    }
    public LoginUserNotExistsException(String message) {
        super(message);
    }
    public LoginUserNotExistsException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
    public LoginUserNotExistsException(Throwable rootCause) {
        super(rootCause);
    }
}
