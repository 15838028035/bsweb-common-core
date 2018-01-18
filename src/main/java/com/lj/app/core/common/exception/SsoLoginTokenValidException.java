package com.lj.app.core.common.exception;

/**
 * 
 * 单点登陆token交验异常
 *
 */
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
