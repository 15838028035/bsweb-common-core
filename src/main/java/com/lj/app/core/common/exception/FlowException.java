package com.lj.app.core.common.exception;

/**
 * 
 * 流程异常
 *
 */
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
