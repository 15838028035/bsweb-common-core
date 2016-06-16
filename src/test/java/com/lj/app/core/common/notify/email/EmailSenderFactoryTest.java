package com.lj.app.core.common.notify.email;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EmailSenderFactoryTest {

	private EmailSenderFactory emailSenderFactory;
	
	@Before
	public void setUp() {
		 emailSenderFactory = new EmailSenderFactory();
	}

	@Test
	public void createEmailSenderImplTest() {
		assertTrue(emailSenderFactory.createEmailSenderImpl().getClass().getName().contains("EmailSenderImpl"));
	}
}
