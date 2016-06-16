package com.lj.app.core.common.util;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PageTest {
	
	private Page page;

	@Before
	public void setUp() {
		page = new Page();
	}

	@Test
	public void pageConstructTest() {
		page = new Page(100, 10);
		assertEquals(0, page.getTotalPages());
		assertEquals(10, page.getRecordsOfPage());
	}

	@Test
	public void setRecordsOfPageTest() {
		page.setRecordsOfPage(300);
		assertEquals(300, page.getRecordsOfPage());
	}

	@Test
	public void setSortnameTest() {
		page.setSortname("sortName1");
		assertEquals("sortName1", page.getSortname());
	}

	@Test
	public void setCurrentPageTest() {
		page.setCurrentPage(2);
		assertEquals(2, page.getCurrentPage());
	}

	@Test
	public void setTotalRecordsTest() {
		page.setTotalRecords(230);
		assertEquals(230, page.getTotalRecords());
	}

	@Test
	public void getCurrentPageSkipResultsTest() {
	}

	@Test
	public void getCurrentPageRecordesIndexStartTest() {
	}

	@Test
	public void getCurrentPageRecordsIndexEndTest() {
	}

	@Test
	public void setSortorderTest() {
		page.setSortorder("desc");
		assertEquals("desc", page.getSortorder());
	}

	@Test
	public void toJsonStringListTest() {
	}

	@Test
	public void toJsonStringObjectTest() {
		page = new Page();
		page.setCurrentPage(1);
		page.setTotalRecords(100);
		
		Map<String,String> mapObj = new HashMap<String,String>();
		
		String pageJsonStr = page.toJsonString(mapObj);
		String expectedPageJsonStr = "";
		assertEquals(expectedPageJsonStr, pageJsonStr);
	}

	@Test
	public void ToMapTest() {
	}

}
