package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FieldConfigBeanTest {

	private FieldConfigBean fieldConfigBean;
	
	@Before
	public void setUp() {
		fieldConfigBean = new FieldConfigBean();
	}

	@Test
	public void setResultColumnTest() {
		fieldConfigBean.setResultColumn("resultColumn");
		assertEquals("fieldConfigBean",fieldConfigBean.getResultColumn());
	}

	@Test
	public void setNameTest() {
		fieldConfigBean.setName("name");
		assertEquals("name",fieldConfigBean.getName());
	}

	@Test
	public void setTypeTest() {
		fieldConfigBean.setType("type");
		assertEquals("type",fieldConfigBean.getType());
	}

	@Test
	public void setXmlTitleTest() {
		fieldConfigBean.setXmlTitle("xmlTitle");
		assertEquals("xmlTitle",fieldConfigBean.getXmlTitle());
	}

	@Test
	public void setUapFieldTest() {
		fieldConfigBean.setUapField("uapField");
		assertEquals("uapField",fieldConfigBean.getUapField());
	}

	@Test
	public void setUapTypeTes() {
		fieldConfigBean.setUapType("uapType");
		assertEquals("uapType",fieldConfigBean.getUapType());
	}

	@Test
	public void setGetTest() {
		fieldConfigBean.setGet("get");
		assertEquals("get",fieldConfigBean.getGet());
	}

	@Test
	public void setSetTest() {
		fieldConfigBean.setSet("set");
		assertEquals("set",fieldConfigBean.getSet());
	}

	@Test
	public void setMainFieldTest() {
		fieldConfigBean.setMainField("mainField");
		assertEquals("mainField",fieldConfigBean.getMainField());
	}

	@Test
	public void setAttrId() {
		fieldConfigBean.setAttrId("attrId");
		assertEquals("attrId",fieldConfigBean.getAttrId());
	}

	@Test
	public void setMapKeyTest() {
		fieldConfigBean.setMapKey("mapKey");
		assertEquals("mapKey",fieldConfigBean.getMapKey());
	}

	@Test
	public void setLockValueTest() {
		fieldConfigBean.setLockValue("lockValue");
		assertEquals("lockValue",fieldConfigBean.getLockValue());
	}

	@Test
	public void setUnlockValue() {
		fieldConfigBean.setUnlockValue("unlockValue");
		assertEquals("unlockValue",fieldConfigBean.getUnlockValue());
	}

}
