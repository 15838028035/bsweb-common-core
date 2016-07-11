package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MagicNumberTest {

	@Test
	public void asssertTest() {
		assertEquals(0,MagicNumber.INT_0);
		assertEquals(3,MagicNumber.INT_3);
		assertEquals(4,MagicNumber.INT_4);
		assertEquals(7,MagicNumber.INT_7);
		assertEquals(10,MagicNumber.INT_10);
		assertEquals(23,MagicNumber.INT_23);
		assertEquals(24,MagicNumber.INT_24);
		assertEquals(60,MagicNumber.INT_60);
		assertEquals(1000,MagicNumber.INT_1000);
		assertEquals(2048,MagicNumber.INT_2048);
	}

}
