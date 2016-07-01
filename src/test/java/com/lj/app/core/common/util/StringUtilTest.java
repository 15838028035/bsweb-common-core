package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testStrToDate() {
	}

	@Test
	public void testMinute2Hour() {
	}

	@Test
	public void testUnescape() {
	}

	@Test
	public void testGetRound() {
	}

	@Test
	public void testStrTosqlDate() {
	}

	@Test
	public void testTransArray() {
	}

	@Test
	public void testZero_StrEx() {
	}

	@Test
	public void testStr2Long() {
	}

	@Test
	public void testSubStringFrontZero() {
	}

	@Test
	public void testDateRandom() {
	}

	@Test
	public void testJavaScriptEscape() {
	}

	@Test
	public void testGetStrLength() {
	}

	@Test
	public void testGetRow() {
	}

	@Test
	public void testGetLengthString() {
	}

	@Test
	public void testIsNotBlank() {
	}

	@Test
	public void testIsBlank() {
	}

	@Test
	public void testIsInt() {
	}

	@Test
	public void testVerifyEmail() {
	}

	@Test
	public void testIsDate() {
	}

	@Test
	public void testIsNumber() {
	}

	@Test
	public void testVerifyIdCardNo() {
	}

	@Test
	public void testVerifyName() {
	}

	@Test
	public void testVerifyLoginName() {
	}

	@Test
	public void testVerifyMobile() {
	}

	@Test
	public void testIncreaseWithout4() {
	}

	@Test
	public void testToUpper() {
	}

	@Test
	public void testIsNull() {
	}

	@Test
	public void testIsNotNull() {
	}

	@Test
	public void testIsEqual() {
	}

	@Test
	public void testIsEqualsIgnoreCase() {
	}

	@Test
	public void testIsEqualsY() {
	}

	@Test
	public void testIsEqualsTrue() {
	}

	@Test
	public void testAddPrefix() {
	}

	@Test
	public void testDelSqlComment() {
	}

	@Test
	public void getUUIDTest() {
		assertNotNull(StringUtil.getUUID());
	}

	@Test
	public void getRandomStringTest() {
		assertTrue(StringUtil.getRandomString(4).length()==4);
		assertTrue(StringUtil.getRandomString(5).length()==5);
		assertTrue(StringUtil.getRandomString(6).length()==6);
	}

	@Test
	public void splitStringTest() {
		String splitString ="abcdefgh";
		assertTrue(StringUtil.splitString(splitString, 2).size()>0);
	}

	@Test
	public void toStringListOfStringStringTest() {
	}
	
	@Test
	public void toStringListTest() {
		assertTrue(StringUtil.toStringList(null).size()==0);
		assertTrue(StringUtil.toStringList("a").size()==1);
		assertTrue(StringUtil.toStringList("a,b").size()==2);
	}
	
	@Test
	public void trimBlankTest() {
		assertEquals("",StringUtil.trimBlank(null));
		assertEquals("",StringUtil.trimBlank(""));
		assertEquals("",StringUtil.trimBlank(" "));
		assertEquals("a",StringUtil.trimBlank("a"));
		assertEquals("a",StringUtil.trimBlank(" a"));
		assertEquals("a",StringUtil.trimBlank("a "));
		assertEquals("a",StringUtil.trimBlank(" a "));
	}

}
