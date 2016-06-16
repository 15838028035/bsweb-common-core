package com.lj.app.core.common.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ServletUtilsTest {
	
	@Test
	public void textTypeTest() {
		assertEquals("text/plain",ServletUtils.TEXT_TYPE);
	}
	
	@Test
	public void jsonTypeTest() {
		assertEquals("application/json",ServletUtils.JSON_TYPE);
	}
	
	@Test
	public void xmlTypeTest() {
		assertEquals("text/xml",ServletUtils.XML_TYPE);
	}
	
	@Test
	public void htmlTypeTest() {
		assertEquals("text/html",ServletUtils.HTML_TYPE);
	}
	
	@Test
	public void jsTypeTest() {
		assertEquals("text/javascript",ServletUtils.JS_TYPE);
	}
	
	@Test
	public void excelTypeTest() {
		assertEquals("application/vnd.ms-excel",ServletUtils.EXCEL_TYPE);
	}
	
	@Test
	public void AuthorizationHeaderTst(){
		assertEquals("Authorization",ServletUtils.AUTHENTICATION_HEADER);
	}
	
	@Test
	public void oneYearSecondsTest(){
		assertEquals(60 * 60 * 24 * 365,ServletUtils.ONE_YEAR_SECONDS);
	}

}
