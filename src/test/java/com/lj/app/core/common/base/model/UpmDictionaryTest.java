package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UpmDictionaryTest {

	private UpmDictionary upmDictionary;
	
	@Before
	public void setUp() {
		upmDictionary = new UpmDictionary();
	}

	@Test
	public void setIdTest() {
		upmDictionary.setId(1);
		assertTrue(1==upmDictionary.getId());
	}

	@Test
	public void setTypeCodeTest() {
		upmDictionary.setTypeCode("typeCode");
		assertEquals("typeCode",upmDictionary.getTypeCode());
	}

	@Test
	public void setDataCodeTest() {
		upmDictionary.setDataCode("dataCode");
		assertEquals("dataCode",upmDictionary.getDataCode());
	}

	@Test
	public void setDataDescTest() {
		upmDictionary.setDataDesc("dataDesc");
		assertEquals("dataDesc",upmDictionary.getDataDesc());
	}

	@Test
	public void setSortNoTest() {
		upmDictionary.setSortNo(1);
		assertTrue(1==upmDictionary.getSortNo());
	}

	@Test
	public void testSetNodeId() {
		upmDictionary.setNodeId(1);
		assertTrue(1==upmDictionary.getNodeId());
	}

}
