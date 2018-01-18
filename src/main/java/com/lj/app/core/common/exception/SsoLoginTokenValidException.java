package com.lj.app.core.common.exception;

@SuppressWarnings("serial")
public class SsoLoginTokenValidException extends CoreBaseRunTimeException {

  public SsoLoginTokenValidException() {
    super();
  }

  public SsoLoginTokenValidException(String message) {
    super(message);
  }

  public SsoLoginTokenValidException(String message, Throwable rootCause) {
    super(message, rootCause);
  }

  public SsoLoginTokenValidException(Throwable rootCause) {
    super(rootCause);
  }
}
