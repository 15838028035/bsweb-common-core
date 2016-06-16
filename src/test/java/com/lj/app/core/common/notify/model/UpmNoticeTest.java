package com.lj.app.core.common.notify.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UpmNoticeTest {

	private UpmNotice upmNotice;
	@Before
	public void setUp() {
		 upmNotice = new UpmNotice();
	}
	
	@Test
	public void setIdTest() {
		upmNotice.setId(1);
		assertTrue(1==upmNotice.getId());
	}

	@Test
	public void setTypeIdTest() {
		upmNotice.setTypeId("mail");
		assertEquals("mail",upmNotice.getTypeId());
	}

	@Test
	public void setContentTest() {
		upmNotice.setContent("content");
		assertEquals("content",upmNotice.getContent());
	}

	@Test
	public void setParamATest() {
		upmNotice.setParamA("setParamA");
		assertEquals("setParamA",upmNotice.getParamA());
	}

	@Test
	public void setParamBTest() {
		upmNotice.setParamB("setParamB");
		assertEquals("setParamB",upmNotice.getParamB());
	}

	@Test
	public void setSendBeginDate() {
		upmNotice.setSendBeginDateBegin("sendBeginDateBegin");
		assertEquals("sendBeginDateBegin",upmNotice.getSendBeginDateBegin());
	}
	
	@Test
	public void setSendEndDateTest() {
		upmNotice.setSendBeginDateEnd("sendBeginDateEnd");
		assertEquals("sendBeginDateEnd",upmNotice.getSendBeginDateEnd());
	}
	
}
