package com.lj.app.core.common.notify.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.util.StringUtil;

/**
 * 
 * 邮件发送服务类
 *
 */
public class EmailService {

  private static Log logger = LogFactory.getLog(EmailService.class);

  /**
   * 发送邮件
   * @param sendTo  接收用户
   * @param subject 邮件主题
   * @param content 邮件内容
   * @return boolean 是否发送成功
   */
  public static boolean sendEmailInfo(String sendTo, String subject, String content) {
    boolean result = false;
    if (StringUtil.isNotBlank(sendTo) && StringUtil.isNotBlank(subject)) {
      IEmailSender emailSender = EmailSenderFactory.createEmailSenderImpl();
      logger.warn("**********start invoke email service:");
      result = emailSender.sendEmail(sendTo, subject, content);
      logger.warn("**********end invoke email service:");
    }
    return result;
  }
}
