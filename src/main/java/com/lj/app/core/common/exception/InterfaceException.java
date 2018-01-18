package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class InterfaceException extends CoreBaseRunTimeException {

  public InterfaceException() {
    super();
  }

  public InterfaceException(String e) {
    super(e);
  }

  public InterfaceException(Throwable cause) {
    super(cause);
  }

  public InterfaceException(String message, Throwable cause) {
    super(message, cause);
  }
}