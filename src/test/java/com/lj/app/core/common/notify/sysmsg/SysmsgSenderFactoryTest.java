package com.lj.app.core.common.notify.sysmsg;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SysmsgSenderFactoryTest {

	private SysmsgSenderFactory sysmsgSenderFactory;
	
	@Before
	public void setUp() {
		sysmsgSenderFactory = new SysmsgSenderFactory();
	}

	@Test
	public void createSysmsgSenderImplTest() {
		assertTrue(sysmsgSenderFactory.createSysmsgSenderImpl().getClass().getName().contains("SysmsgSenderImpl"));
	}
	
}
