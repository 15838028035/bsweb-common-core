package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.base.entity.UpmFile;

public class UpmFileTest {

	private UpmFile upmFile;
	
	@Before
	public void setUp() {
		upmFile  = new UpmFile();
	}
	
	@Test
	public void setIdTest() {
		upmFile.setId(1);
		assertTrue(1==upmFile.getId());
	}

	@Test
	public void setRelateId1Test() {
		upmFile.setRelateId1("relateId1");
		assertEquals("relateId1",upmFile.getRelateId1());
	}

	@Test
	public void setRelateId2Test() {
		upmFile.setRelateId2("relateId2");
		assertEquals("relateId2",upmFile.getRelateId2());
	}
	
	@Test
	public void setNameTest() {
		upmFile.setName("name");
		assertEquals("name",upmFile.getName());
	}

	@Test
	public void setOptdateTest() {
		upmFile.setOptdate(new Date());
		assertNotNull(upmFile.getOptdate());
	}

	@Test
	public void setOperatorTest() {
		upmFile.setOperator(11);
		assertTrue(11==upmFile.getOperator());
	}

	@Test
	public void setOptdateBeginTest() {
		upmFile.setOptdateBegin("optdateBegin");
		assertEquals("optdateBegin",upmFile.getOptdateBegin());
	}

	@Test
	public void setOptdateEndTest() {
		upmFile.setOptdateEnd("optdateEnd");
		assertEquals("optdateEnd",upmFile.getOptdateEnd());
	}

	@Test
	public void testSetContent() {
		byte[] content = new byte[20];
		upmFile.setContent(content);
		assertNotNull(upmFile.getContent());
	}

}
