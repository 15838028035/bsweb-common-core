package com.lj.app.core.common.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CMSecurityContextTest {

	private CMSecurityContext cMSecurityContext;
	
	@Before
	public void setUp() {
		cMSecurityContext = new CMSecurityContext();
	}

	@Test
	public void setTestLoginTime() {
		cMSecurityContext.setLoginTime("2015-10-10 10:00:00");
		assertEquals("LoginTime must be 2015-10-10 10:00:00",cMSecurityContext.getLoginTime());
	}

	@Test
	public void hasUrlPermissionTest() {
		Set<String> urls = new HashSet();
		urls.add("url1");
		urls.add("url2");
		urls.add("url3");
		urls.add("url4");
		cMSecurityContext.setUrls(urls);
		assertEquals("urls size must be 4",4,cMSecurityContext.getUrls().size());
		
		assertTrue(cMSecurityContext.hasUrlPermission("url1"));
		assertTrue(cMSecurityContext.hasUrlPermission("url2"));
		assertTrue(cMSecurityContext.hasUrlPermission("url3"));
		assertTrue(cMSecurityContext.hasUrlPermission("url4"));
		
		assertFalse(cMSecurityContext.hasUrlPermission("urlNotExists"));
	}

	@Test
	public void hasDisplayPermissionTest() {
		Set<String> codes = new HashSet();
		codes.add("codes1");
		codes.add("codes2");
		codes.add("codes3");
		codes.add("codes4");
		cMSecurityContext.setCodes(codes);
		assertEquals("codes size must be 4",4,cMSecurityContext.getCodes().size());
		
		assertTrue(cMSecurityContext.hasDisplayPermission("codes1"));
		assertTrue(cMSecurityContext.hasDisplayPermission("codes2"));
		assertTrue(cMSecurityContext.hasDisplayPermission("codes3"));
		assertTrue(cMSecurityContext.hasDisplayPermission("codes4"));
		
		assertFalse(cMSecurityContext.hasDisplayPermission("codesNotExists"));
		
	}


	@Test
	public void setCodesTest() {
		Set<String> codes = new HashSet();
		codes.add("codes1");
		codes.add("codes2");
		codes.add("codes3");
		codes.add("codes4");
		cMSecurityContext.setCodes(codes);
		assertEquals("codes size must be 4",4,cMSecurityContext.getCodes().size());
	}

	@Test
	public void setGetMainAcctIdTest() {
		cMSecurityContext.setMainAcctId(1L);
		assertEquals("1",cMSecurityContext.getMainAcctId().toString());
	}

	@Test
	public void setGetLoginNameTest() {
		cMSecurityContext.setLoginName("loginName");
		assertEquals("loginName",cMSecurityContext.getLoginName());
	}

	@Test
	public void setUrlsTest() {
		Set<String> urls = new HashSet();
		urls.add("url1");
		urls.add("url2");
		urls.add("url3");
		urls.add("url4");
		cMSecurityContext.setUrls(urls);
		assertEquals("urls size must be 4",4,cMSecurityContext.getUrls().size());
	}

}
