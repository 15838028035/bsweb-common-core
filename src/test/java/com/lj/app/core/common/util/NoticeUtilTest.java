package com.lj.app.core.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.notify.email.EmailSenderFactory;
import com.lj.app.core.common.notify.email.IEmailSender;

public class NoticeUtilTest {
	
	@Before
	public void setUp() {
		NoticeUtil noticeUtil = new NoticeUtil();
		
		NoticeUtil.setDefaultEmailSender(EmailSenderFactory.createEmailSenderImpl());
		String name = NoticeUtil.getDefaultEmailSender().getClass().getName();
		assertTrue(name.contains("EmailSenderImpl"));
	}
	
	@Test
	public void sendEmailTest()  throws Exception {
		NoticeUtil.setEmailSender(null);
		boolean sendResult = NoticeUtil.sendEmail("emailAddress", "title", "content");
		assertTrue(sendResult);
	}

	@Test
	public void sendEmailFakeTest() throws Exception {
		NoticeUtil.setEmailSender(new IEmailSenderFake());
		boolean sendResult = NoticeUtil.sendEmail("emailAddress", "title", "content");
		assertTrue(sendResult);
	}
	
	@Test
	public void sendEmailExceptionTest() throws Exception {
		NoticeUtil.setEmailSender(new IEmailSenderException());
		boolean sendResult = NoticeUtil.sendEmail("emailAddress", "title", "content");
		assertFalse(sendResult);
	}
	

	private class IEmailSenderFake implements IEmailSender{

		@Override
		public boolean sendEmail(String sendTo, String subject, String content) {
			return true;
		}
		
	}
	
	private class IEmailSenderException implements IEmailSender{

		@Override
		public boolean sendEmail(String sendTo, String subject, String content)  {
			return false;
		}
		
	}
}
