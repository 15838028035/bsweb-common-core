package com.lj.app.core.common.notify.sms;

/**
 * 
 * 短信工厂类
 *
 */
public class SmsSenderFactory {

  /**
   * 短信发送服务
   * 
   * @return 短信发送服务
   */
  public static ISmsSender createSmsSenderImpl() {
    return new SmsSenderImpl();
  }
}
