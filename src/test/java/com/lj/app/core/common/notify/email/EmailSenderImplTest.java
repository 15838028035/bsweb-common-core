package com.lj.app.core.common.notify.email;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class EmailSenderImplTest extends AbstractBaseSpringTransactionTestCase{
	
	private IEmailSender emailSender;

	@Before
	public void setUp() {
		emailSender = new EmailSenderImpl();
	}

	@Test
	public void sendEmailTest() throws Exception{
		assertTrue(emailSender.sendEmail("sendTo", "subject", "content"));
	}
	
	@Test
	public void sendEmailSentToNullTest() throws Exception{
		assertFalse(emailSender.sendEmail("", "subject", "content"));
	}
	
	@Test
	public void sendEmailExceptionTest() throws Exception{
		String content="";
		
		for(int i=0; i<=10000;i++) {
			content = content + "内容太长了\n";
		}
		
		assertFalse(emailSender.sendEmail("sendTo", "subject", content));
	}

}
