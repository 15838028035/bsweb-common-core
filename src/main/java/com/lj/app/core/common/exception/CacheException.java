package com.lj.app.core.common.exception;

public class CacheException extends CoreBaseRunTimeException {

  public CacheException() {
    super();
  }

  public CacheException(String message) {
    super(message);
  }

  public CacheException(Throwable cause) {
    super(cause);
  }

  public CacheException(String message, Throwable cause) {
    super(message, cause);
  }
}
