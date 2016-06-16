package com.lj.app.core.common.notify.sysmsg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class SysmsgServiceTest extends AbstractBaseSpringTransactionTestCase{

	@Test
	public void sendSmsInfoTest() {
		Long mainAcctId = 1L;
		assertTrue(SysmsgService.sendSmsInfo("title", "content", mainAcctId));
	}
	
	@Test
	public void sendSmsInfoExceptionTest() {
		String content="";
		
		for(int i=0; i<=10000;i++) {
			content = content + "内容太长了\n";
		}
		
		Long mainAcctId = 1L;
		assertFalse(SysmsgService.sendSmsInfo("title", content, mainAcctId));
	}

}
