package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EncodeUtilTest {

	@Test
	public void hexEncodeDecodeTest() {
		 byte []hexEncode = EncodeUtil.hexDecode("hex");
		 assertNotNull(hexEncode);
		 
		String hexDecode = EncodeUtil.hexEncode(hexEncode);
		assertEquals("hex",hexDecode);
	}

	@Test
	public void base64EncodeDecodeTest() {
		 byte []base64Encode = EncodeUtil.base64Decode("base64");
		 assertNotNull(base64Encode);
		 
		String base64Decode = EncodeUtil.hexEncode(base64Encode);
		assertEquals("base64",base64Decode);
	}

	@Test
	public void base64UrlSafeEncodeTest() {
		 byte []base64SafeCodeArray = EncodeUtil.base64Decode("base64SafeCode");
		String base64SafeCode = EncodeUtil.base64UrlSafeEncode(base64SafeCodeArray);
		assertNotNull(base64SafeCode);
	}

	@Test
	public void urlEncoderDecodeTest() {
		String encodUrl = EncodeUtil.urlEncode("encodUrl");
		String decodUrl = EncodeUtil.urlDecode(encodUrl);
		assertEquals("encodUrl",decodUrl);
	}
	
	@Test
	public void urlEncoderDecodeWithGBKTest() {
		String encodUrl = EncodeUtil.urlEncode("encodUrl","GBK");
		String decodUrl = EncodeUtil.urlDecode(encodUrl,"GBK");
		assertEquals("encodUrl",decodUrl);
	}

	@Test
	public void htmlEscapeTest() {
		String htmlEscape = EncodeUtil.htmlEscape("htmlEscape");
		assertEquals("htmlEscape",htmlEscape);
	}

	@Test
	public void htmlUnescapeTest() {
		String htmlUnescape = EncodeUtil.htmlUnescape("htmlUnescape");
		assertEquals("htmlUnescape",htmlUnescape);
		
	}

	@Test
	public void xmlEscapeTest() {
		String xmlEscape = EncodeUtil.xmlEscape("xmlEscape");
		assertEquals("xmlEscape",xmlEscape);
	}

	@Test
	public void xmlUnescapeTest() {
		String xmlUnescape = EncodeUtil.htmlUnescape("xmlUnescape");
		assertEquals("xmlUnescape",xmlUnescape);
	}

}
