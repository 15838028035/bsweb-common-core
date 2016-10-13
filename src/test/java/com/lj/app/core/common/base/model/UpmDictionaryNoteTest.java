package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.base.entity.UpmDictionaryNote;

public class UpmDictionaryNoteTest {

	private UpmDictionaryNote upmDictionaryNote;
	
	@Before
	public void setUp(){
		upmDictionaryNote = new UpmDictionaryNote();
	}

	@Test
	public void setIdTest() {
		upmDictionaryNote.setId(1);
		assertTrue(1==upmDictionaryNote.getId());
	}

	@Test
	public void setTypeCodeTest() {
		upmDictionaryNote.setTypeCode("typeCode");
		assertEquals("typeCode",upmDictionaryNote.getTypeCode());
	}

	@Test
	public void setTypeDescTest() {
		upmDictionaryNote.setTypeDesc("typeDesc");
		assertEquals("typeDesc",upmDictionaryNote.getTypeDesc());
	}

}
