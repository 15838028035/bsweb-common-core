package com.lj.app.core.common.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CacheUtilTest {
	
	private CacheUtil cacheUtil;
	
	@Before
	public void setUp() {
		cacheUtil = new CacheUtil();
		cacheUtil.clealAll();
	}

	@Test
	public void storeTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		
		assertNotNull(CacheUtil.find("key"));
		assertEquals("value1",CacheUtil.find("key"));
	}

	@Test
	public void findTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		
		assertNotNull(CacheUtil.find("key"));
		assertEquals("value1",CacheUtil.find("key"));
	}
	
	@Test
	public void findKeyNotExitsTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		
		assertNotNull(CacheUtil.find("key"));
		assertNull(CacheUtil.find("keyNotExists"));
	}

	@Test
	public void removeCacheTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		assertTrue(CacheUtil.getCacheSize()==1);
		CacheUtil.store("key2", "value2");
		assertTrue(CacheUtil.getCacheSize()==2);
		CacheUtil.store("key3", "value3");
		assertTrue(CacheUtil.getCacheSize()==3);
		CacheUtil.removeCache("key3");
		assertTrue(CacheUtil.getCacheSize()==2);
	}

	@Test
	public void hasStoreTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		assertTrue(CacheUtil.hasStore("key"));
		assertFalse(CacheUtil.hasStore("keyNotExists"));
	}

	@Test
	public void getCacheSizeTest() {
		CacheUtil.clealAll();
		CacheUtil.store("key", "value1");
		assertTrue(CacheUtil.getCacheSize()==1);
		CacheUtil.store("key2", "value2");
		assertTrue(CacheUtil.getCacheSize()==2);
		CacheUtil.store("key3", "value3");
		assertTrue(CacheUtil.getCacheSize()==3);
		CacheUtil.removeCache("key3");
		assertTrue(CacheUtil.getCacheSize()==2);
	}

}
