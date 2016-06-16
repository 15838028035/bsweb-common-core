package com.lj.app.core.common.notify.email;


public class EmailSenderFactory {
	
	public static IEmailSender createEmailSenderImpl(){
		return new EmailSenderImpl();
	}
}
