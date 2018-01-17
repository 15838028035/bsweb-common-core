package com.lj.app.core.common.notify.sysmsg;

/**
 * 
 * SysmsgSenderFactory
 *
 */
public class SysmsgSenderFactory {

  public static ISysmsgSender createSysmsgSenderImpl() {
    return new SysmsgSenderImpl();
  }
}