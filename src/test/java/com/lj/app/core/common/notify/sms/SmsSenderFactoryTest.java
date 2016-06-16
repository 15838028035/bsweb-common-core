package com.lj.app.core.common.notify.sms;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SmsSenderFactoryTest {

	private SmsSenderFactory smsSenderFactory;
	
	@Before
	public void setUp() {
		smsSenderFactory = new SmsSenderFactory();
	}
	
	@Test
	public void createSmsSenderImplTest() {
		assertTrue(smsSenderFactory.createSmsSenderImpl().getClass().getName().contains("SmsSenderImpl"));
	}

}
