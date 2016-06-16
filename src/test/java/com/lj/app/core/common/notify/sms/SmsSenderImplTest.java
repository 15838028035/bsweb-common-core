package com.lj.app.core.common.notify.sms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class SmsSenderImplTest  extends AbstractBaseSpringTransactionTestCase{
	
	private ISmsSender iSmsSender;
	
	@Before
	public void setUp() {
		iSmsSender = new SmsSenderImpl();
	}

	@Test
	public void sendSmsTest() throws Exception{
		String mobile ="mobile";
		String content = "mobile";
		Timestamp beginTime=null;
		Timestamp endTime = null;
		assertTrue(iSmsSender.sendSms(mobile, content, beginTime, endTime));
	}
	
	@Test
	public void sendSmsTestMullException() throws Exception{
		String mobile ="";
		String content = "";
		Timestamp beginTime=null;
		Timestamp endTime = null;
		assertFalse(iSmsSender.sendSms(mobile, content, beginTime, endTime));
	}
	
	@Test
	public void sendSmsTestExceptionTest() throws Exception{
		String content="";
		
		for(int i=0; i<=10000;i++) {
			content = content + "内容太长了\n";
		}
		
		assertFalse(iSmsSender.sendSms("mobile", content, null, null));
	}

}
