package com.lj.app.core.common.notify.email;

/**
 * 邮件发送接口
 *
 */
public interface IEmailSender {
	public boolean sendEmail(String sendTo, String subject, String content);
}