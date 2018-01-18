package com.lj.app.core.common.exception;

/**
 * 
 * SSL登陆用户不存在异常
 *
 */
@SuppressWarnings("serial")
public class SslLoginUserNotExistsException extends CoreBaseRunTimeException {

  public SslLoginUserNotExistsException() {
    super();
  }

  public SslLoginUserNotExistsException(String message) {
    super(message);
  }

  public SslLoginUserNotExistsException(String message, Throwable rootCause) {
    super(message, rootCause);
  }

  public SslLoginUserNotExistsException(Throwable rootCause) {
    super(rootCause);
  }
}
