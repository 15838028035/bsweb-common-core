package com.lj.app.core.common.notify.sysmsg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class SysmsgSenderImplTest extends AbstractBaseSpringTransactionTestCase{
	
	private ISysmsgSender sysmsgSender;
	
	@Before
	public void setUp() {
		sysmsgSender = new SysmsgSenderImpl();
	}

	@Test
	public void sendSysmsgTest() {
		Long mainAcctId = 1L;
		assertTrue(sysmsgSender.sendSysmsg("title", "content", mainAcctId));
	}
	
	@Test
	public void sendSysmsgExceptionTest() {
		
		String content="";
		
		for(int i=0; i<=10000;i++) {
			content = content + "内容太长了\n";
		}
		
		Long mainAcctId = 1L;
		assertFalse(sysmsgSender.sendSysmsg("title", content, mainAcctId));
	}

}
