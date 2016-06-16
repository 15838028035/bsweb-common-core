package com.lj.app.core.common.audit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CMCodeTest {

	@Test
	public void lockStatustest() {
		assertEquals("0",CMCode.LOCK_SATE_0);
		assertEquals("1",CMCode.LOCK_SATE_1);
	}

}
