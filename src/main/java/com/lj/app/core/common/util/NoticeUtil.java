package com.lj.app.core.common.util;

import com.lj.app.core.common.notify.email.EmailSenderFactory;
import com.lj.app.core.common.notify.email.IEmailSender;

public class NoticeUtil {
	
	private static IEmailSender emailSender;
	
	private static IEmailSender defaultEmailSender =  EmailSenderFactory.createEmailSenderImpl();
	
	public static boolean  sendEmail(String emailAddress,String title,String content) throws Exception{
		
		try {
			IEmailSender emailSender = getEmailSender();
			return emailSender.sendEmail(emailAddress, title, content);
		} catch (Exception e) {
			return false;
		}
	}

	public static IEmailSender getEmailSender() {
		return emailSender == null?defaultEmailSender:emailSender;
	}

	public static void setEmailSender(IEmailSender emailSender) {
		NoticeUtil.emailSender = emailSender;
	}

	public static IEmailSender getDefaultEmailSender() {
		return defaultEmailSender;
	}

	public static void setDefaultEmailSender(IEmailSender defaultEmailSender) {
		NoticeUtil.defaultEmailSender = defaultEmailSender;
	}
	
}
