package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void trimBlankTest() {
		assertEquals("",StringUtil.trimBlank(null));
		assertEquals("",StringUtil.trimBlank(""));
		assertEquals("",StringUtil.trimBlank(" "));
		assertEquals("a",StringUtil.trimBlank("a"));
		assertEquals("a",StringUtil.trimBlank(" a"));
		assertEquals("a",StringUtil.trimBlank("a "));
		assertEquals("a",StringUtil.trimBlank(" a "));
	}

	@Test
	public void toStringTest() {
		assertEquals("",StringUtil.toString(null));
		assertEquals(" ",StringUtil.toString(" "));
		assertEquals("abc",StringUtil.toString("abc"));
		assertEquals("abc",StringUtil.toString("abc ").trim());
	}
	
	@Test
	public void isBlankTest() {
		assertTrue(StringUtil.isBlank(""));
		assertTrue(StringUtil.isBlank(" "));
		assertFalse(StringUtil.isBlank(" a"));
		assertFalse(StringUtil.isBlank("t"));
		
		assertFalse(StringUtil.isBlank(("a,b,c").split(",")));
	}
	
	@Test
	public void isNotBlankTest() {
		assertFalse(StringUtil.isNotBlank(""));
		assertFalse(StringUtil.isNotBlank(" "));
		assertTrue(StringUtil.isNotBlank(" a"));
		assertTrue(StringUtil.isNotBlank("t"));
		
		assertTrue(StringUtil.isNotBlank(("a,b,c").split(",")));
	}

	@Test
	public void isNullTest() {
		assertTrue(StringUtil.isNull(""));
		assertTrue(StringUtil.isNull(" "));
		assertFalse(StringUtil.isNull(" a"));
		assertFalse(StringUtil.isNull("t"));
	}

	@Test
	public void isNotNullTest() {
		assertFalse(StringUtil.isNotNull(""));
		assertFalse(StringUtil.isNotNull(" "));
		assertTrue(StringUtil.isNotNull(" a"));
		assertTrue(StringUtil.isNotNull("t"));
	}

	@Test
	public void isEqualTest() {
		assertFalse(StringUtil.isEqual(null, ""));
		assertFalse(StringUtil.isEqual("a", "A"));
		assertFalse(StringUtil.isEqual("a", "ab"));
		assertTrue(StringUtil.isEqual("a", "a"));
		assertFalse(StringUtil.isEqual("a", "a "));
		assertFalse(StringUtil.isEqual("a", "a "));
		assertFalse(StringUtil.isEqual("a", " a"));
		assertFalse(StringUtil.isEqual("a", " a "));
	}

	@Test
	public void isEqualsIgnoreCaseTest() {
		assertFalse(StringUtil.isEqualsIgnoreCase(null, ""));
		assertTrue(StringUtil.isEqualsIgnoreCase("a", "A"));
		assertFalse(StringUtil.isEqualsIgnoreCase("a", "ab"));
		assertTrue(StringUtil.isEqualsIgnoreCase("a", "a"));
		assertFalse(StringUtil.isEqualsIgnoreCase("a", "a "));
		assertFalse(StringUtil.isEqualsIgnoreCase("a", "a "));
		assertFalse(StringUtil.isEqualsIgnoreCase("a", " a"));
		assertFalse(StringUtil.isEqualsIgnoreCase("a", " a "));
	}

	@Test
	public void isEqualsYTest() {
		assertFalse(StringUtil.isEqualsY(null));
		assertFalse(StringUtil.isEqualsY("a"));
		assertTrue(StringUtil.isEqualsY("y"));
		assertTrue(StringUtil.isEqualsY("Y"));
		assertFalse(StringUtil.isEqualsY("y "));
		assertFalse(StringUtil.isEqualsY(" y "));
		assertFalse(StringUtil.isEqualsY(" y "));
		assertFalse(StringUtil.isEqualsY("Y "));
		assertFalse(StringUtil.isEqualsY(" Y "));
		assertFalse(StringUtil.isEqualsY(" Y "));
	}

	@Test
	public void isEqualsTrueTest() {
		assertFalse(StringUtil.isEqualsTrue(null));
		assertFalse(StringUtil.isEqualsTrue("a"));
		assertTrue(StringUtil.isEqualsTrue("true"));
		assertTrue(StringUtil.isEqualsTrue("true"));
		assertFalse(StringUtil.isEqualsTrue("true "));
		assertFalse(StringUtil.isEqualsTrue(" TRUE "));
		assertFalse(StringUtil.isEqualsTrue(" TRUE "));
		assertFalse(StringUtil.isEqualsTrue("TRUE "));
		assertFalse(StringUtil.isEqualsTrue(" TRUE "));
		assertFalse(StringUtil.isEqualsTrue(" TRUE "));
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
		assertTrue(StringUtil.getRandomString(4).length()==3);
		assertTrue(StringUtil.getRandomString(5).length()==4);
		assertTrue(StringUtil.getRandomString(6).length()==5);
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
	public void splitStringToStringListTest() {
		assertTrue(StringUtil.splitStringToStringList(null).size()==0);
		assertTrue(StringUtil.splitStringToStringList("a").size()==1);
		assertTrue(StringUtil.splitStringToStringList("a,b").size()==2);
	}
	
	@Test
	public void parseIntTest() {
		assertEquals(1,StringUtil.parseInt("1", 3));
		assertEquals(3,StringUtil.parseInt("1a", 3));
		assertEquals(3,StringUtil.parseInt(" 1 ", 3));
	}
	
	@Test
	public void parseFloatTest() {
		assertEquals(1.0,StringUtil.parseFloat("1", 3));
		assertEquals(3.0,StringUtil.parseFloat("1a", 3));
		assertEquals(3.0,StringUtil.parseFloat(" 1 ", 3));
	}
	
	@Test
	public void toChiTest() {
		assertNotNull(StringUtil.toChi("a"));
		assertNotNull(StringUtil.toChi("你好"));
	}
	
	@Test
	public void toISOTest() {
		assertNotNull(StringUtil.toISO("a"));
		assertNotNull(StringUtil.toISO("你好"));
	}
	
	@Test
	public void changeEncodingTest(){
		assertNotNull(StringUtil.changeEncoding("a", "GBK", "ISO8859-1"));
		assertNotNull(StringUtil.changeEncoding("你好", "GBK", "ISO8859-1"));
	}
	
	@Test
	public void replaceTest() {
		
	}
	
	@Test
	public void getExtensionTest() {
		assertEquals("xls",StringUtil.getExtension("1.xls"));
		assertEquals("xlsx",StringUtil.getExtension("1.xlsx"));
		assertEquals("doc",StringUtil.getExtension("1.doc"));
		assertEquals("docx",StringUtil.getExtension("1.docx"));
		assertEquals("pdf",StringUtil.getExtension("1.pdf"));
		assertEquals("jpg",StringUtil.getExtension("1.jpg"));
		assertEquals("gif",StringUtil.getExtension("1.gif"));
		assertEquals("png",StringUtil.getExtension("1.png"));
		assertEquals("text",StringUtil.getExtension("1.text"));
		assertEquals("txt",StringUtil.getExtension("1.txt"));
		assertEquals("sql",StringUtil.getExtension("1.sql"));
		assertEquals("java",StringUtil.getExtension("1.java"));
		assertEquals("js",StringUtil.getExtension("1.js"));
		assertEquals("jsp",StringUtil.getExtension("1.jsp"));
	}
	
	@Test
	public void getPrefixTest() {
		assertEquals("1",StringUtil.getPrefix("1.xls"));
		assertEquals("2",StringUtil.getPrefix("2.xlsx"));
		assertEquals("3",StringUtil.getPrefix("3.doc"));
		assertEquals("4",StringUtil.getPrefix("4.docx"));
		assertEquals("5",StringUtil.getPrefix("5.pdf"));
		assertEquals("6",StringUtil.getPrefix("6.jpg"));
		assertEquals("7",StringUtil.getPrefix("7.gif"));
		assertEquals("8",StringUtil.getPrefix("8.png"));
		assertEquals("9",StringUtil.getPrefix("9.text"));
		assertEquals("10",StringUtil.getPrefix("10.txt"));
		assertEquals("11",StringUtil.getPrefix("11.sql"));
		assertEquals("12",StringUtil.getPrefix("12.java"));
		assertEquals("13",StringUtil.getPrefix("13.js"));
		assertEquals("14",StringUtil.getPrefix("14.jsp"));
	}
	
	@Test
	public void getShortFileNameTest() {
		//TODO:test me
	}

}
