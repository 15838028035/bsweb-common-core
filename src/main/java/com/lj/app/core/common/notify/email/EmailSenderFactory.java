package com.lj.app.core.common.notify.email;

/**
 * 
 * 邮件发送工厂
 *
 */
public class EmailSenderFactory {

  public static IEmailSender createEmailSenderImpl() {
    return new EmailSenderImpl();
  }
}
