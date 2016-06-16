package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class InvalidateInputException extends CoreBaseRunTimeException {
    
    public InvalidateInputException() {
        super();
    }
    public InvalidateInputException(String message) {
        super(message);
    }
    public InvalidateInputException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
    public InvalidateInputException(Throwable rootCause) {
        super(rootCause);
    }
}

