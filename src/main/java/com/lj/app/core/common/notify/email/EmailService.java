package com.lj.app.core.common.notify.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.util.StringUtil;

public class EmailService {

	private static Log logger = LogFactory.getLog(EmailService.class);

	public static boolean sendEmailInfo(String sendTo, String subject,String content) {
		boolean result = false;
		if (StringUtil.isNotBlank(sendTo) && StringUtil.isNotBlank(subject)) {
			IEmailSender emailSender = EmailSenderFactory
					.createEmailSenderImpl();
			logger.warn("**********start invoke email service:");
			result = emailSender.sendEmail(sendTo, subject, content);
			logger.warn("**********end invoke email service:");
		}
		return result;
	}
}
