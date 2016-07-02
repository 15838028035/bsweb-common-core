package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void strToDateTest() throws Exception {
		assertNull(StringUtil.strToDate(null, null));
		assertNotNull(StringUtil.strToDate("2016-01-02", "yyyy-MM-dd"));
		assertNotNull(StringUtil.strToDate("2016-01-02 10:00:00", "yyyy-MM-dd HH:mm:ss"));
		assertNotNull(StringUtil.strToDate("20160102", "yyyyMMdd"));
		assertNotNull(StringUtil.strToDate("10:00:00", "HH:mm:ss"));
	}
	
	@Test(expected = Exception.class)
	public void strToDateExceptionTest() throws Exception {
		assertNull(StringUtil.strToDate("2016-01-02", "bad format"));
		assertNull(StringUtil.strToDate("2016-01-02 10:00:00", "bad format"));
		assertNull(StringUtil.strToDate("20160102", "bad format"));
		assertNull(StringUtil.strToDate("10:00:00", "bad format"));
	}

	@Test
	public void minute2HourTest() {
		assertEquals("0.00",StringUtil.minute2Hour("0"));
		assertEquals("0.25",StringUtil.minute2Hour("15"));
		assertEquals("0.50",StringUtil.minute2Hour("30"));
		assertEquals("0.75",StringUtil.minute2Hour("45"));
		assertEquals("1.00",StringUtil.minute2Hour("60"));
		assertEquals("2.00",StringUtil.minute2Hour("120"));
	}

	@Test
	public void unescape() {
		//TODO:test me
	}

	@Test
	public void getRoundTest() {
		//TODO: test me
	}

	@Test(expected = IllegalStateException.class)
	public void strTosqlDateTest() {
		assertNull(StringUtil.strTosqlDate(null, "yyyy-MM-dd"));
		assertNull(StringUtil.strTosqlDate("", "yyyy-MM-dd"));
		
		assertNotNull(StringUtil.strTosqlDate("2016-06-10", "yyyy-MM-dd"));
		assertNotNull(StringUtil.strTosqlDate("2016-06-10 10:00:00", "yyyy-MM-dd HH:mm:ss"));
		
		assertNull(StringUtil.strTosqlDate("2016-06-10", "bad format"));
		assertNull(StringUtil.strTosqlDate("bad date", "yyyy-MM-dd"));
	}

	@Test
	public void transArrayTest() {
		assertEquals("a,b,c",StringUtil.transArray(("a,b,c").split(",")));
	}

	@Test
	public void zero_StrExTest() {
		assertEquals("00001",StringUtil.Zero_StrEx("1", 5));
		assertEquals("00002",StringUtil.Zero_StrEx("2", 5));
		assertEquals("00010",StringUtil.Zero_StrEx("10", 5));
		assertEquals("00100",StringUtil.Zero_StrEx("100", 5));
		assertEquals("01000",StringUtil.Zero_StrEx("1000", 5));
		assertEquals("10000",StringUtil.Zero_StrEx("10000", 5));
	}

	@Test
	public void subStringFrontZeroTest() {
		assertNull(StringUtil.subStringFrontZero(null));
		assertEquals("",StringUtil.subStringFrontZero(""));
		assertEquals("123",StringUtil.subStringFrontZero("12301000"));
		assertEquals("123456",StringUtil.subStringFrontZero("123456"));
	}

	@Test
	public void dateRandomTest() {
		assertTrue(StringUtil.dateRandom().length()>0);
	}

	@Test
	public void javaScriptEscapeTest() {
		assertTrue(StringUtil.javaScriptEscape("\t").contains("\\t"));
		assertTrue(StringUtil.javaScriptEscape("\n").contains("\\n"));
		assertTrue(StringUtil.javaScriptEscape("\t").contains("\\t"));
		assertTrue(StringUtil.javaScriptEscape("/").contains("\\/"));
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
	public void isIntTest() {
		assertTrue(StringUtil.isInt("1"));
		assertFalse(StringUtil.isInt("1a"));
		assertFalse(StringUtil.isInt(null));
		assertFalse(StringUtil.isInt(""));
		assertFalse(StringUtil.isInt(" "));
		assertFalse(StringUtil.isInt("1 "));
	}

	@Test
	public void verifyEmailTest() {
		assertTrue(StringUtil.verifyEmail("zhangsan@126.com"));
		assertTrue(StringUtil.verifyEmail("zhangsan@163.com"));
		
		assertTrue(StringUtil.verifyEmail(null));
		assertTrue(StringUtil.verifyEmail(""));
		assertFalse(StringUtil.verifyEmail(" "));
		assertFalse(StringUtil.verifyEmail("bad email "));
	}

	@Test
	public void isDateTest() {
		assertTrue(StringUtil.isDate("2016-06-10", "yyyy-MM-dd"));
		assertTrue(StringUtil.isDate("20160610", "yyyyMMdd"));
		assertTrue(StringUtil.isDate("2016-06-10 10:00:00", "yyyy-MM-dd HH:mm:ss"));
		assertTrue(StringUtil.isDate("2016-06-10", "bad format"));
		
		assertFalse(StringUtil.isDate(null, "yyyy-MM-dd"));
		assertFalse(StringUtil.isDate("", "yyyy-MM-dd"));
		assertFalse(StringUtil.isDate("", "yyyy-MM-dd"));
		assertFalse(StringUtil.isDate("bad date", "yyyy-MM-dd"));
	}

	@Test
	public void isNumberTest() {
		assertTrue(StringUtil.isNumber("1"));
		assertTrue(StringUtil.isNumber("1.0f"));
		
		assertFalse(StringUtil.isNumber(null));
		assertFalse(StringUtil.isNumber(""));
		assertFalse(StringUtil.isNumber(" "));
		assertFalse(StringUtil.isNumber("a"));
		assertFalse(StringUtil.isNumber(" a "));
		assertFalse(StringUtil.isNumber("1.0 "));
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
		
		System.out.println(StringUtil.getRandomString(4));
		System.out.println(StringUtil.getRandomString(5));
		System.out.println(StringUtil.getRandomString(6));
		
		assertTrue(StringUtil.getRandomString(4).length()>0);
		assertTrue(StringUtil.getRandomString(5).length()>0);
		assertTrue(StringUtil.getRandomString(6).length()>0);
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
		assertTrue(StringUtil.splitStringToStringList(null).size()==1);
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
		assertTrue(Float.parseFloat("1")==StringUtil.parseFloat("1", 3.0f));
		assertTrue(Float.parseFloat("3.0")==StringUtil.parseFloat("1a", 3.0f));
		assertTrue(Float.parseFloat("1.0")==StringUtil.parseFloat(" 1 ", 3.0f));
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
