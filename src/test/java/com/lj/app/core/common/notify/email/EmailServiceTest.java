package com.lj.app.core.common.notify.email;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;


public class EmailServiceTest extends AbstractBaseSpringTransactionTestCase{

	private EmailService emailService;
	
	@Before
	public void setUp() {
		emailService = new EmailService();
	}

	@Test
	public void sendEmailInfoTest() throws Exception{
		assertTrue(emailService.sendEmailInfo("sendTo", "subject", "content"));
	}
	
	@Test
	public void sendEmailInfoSendToNullTest() throws Exception{
		assertFalse(emailService.sendEmailInfo("", "subject", "content"));
	}
	
	@Test
	public void sendEmailInfoExceptionTest() throws Exception{
		String content="";
		
		for(int i=0; i<=10000;i++) {
			content = content + "内容太长了\n";
		}
		
		assertFalse(emailService.sendEmailInfo("sendTo", "subject", content));
	}
}
