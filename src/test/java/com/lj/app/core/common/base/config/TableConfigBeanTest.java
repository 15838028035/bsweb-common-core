package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TableConfigBeanTest {

	private TableConfigBean tableConfigBean;
	
	@Before
	public void setUp() {
		tableConfigBean = new TableConfigBean();
	}
	
	@Test
	public void setNameTest() {
		tableConfigBean.setName("name");
		assertEquals("name",tableConfigBean.getName());
	}

	@Test
	public void setSqlTest() {
		tableConfigBean.setSql("sql");
		assertEquals("sql",tableConfigBean.getSql());
	}

	@Test
	public void setTypeTest() {
		tableConfigBean.setType("type");
		assertEquals("type",tableConfigBean.getType());
	}

	@Test
	public void setDatasrcnameTest() {
		tableConfigBean.setDatasrcname("datasrcname");
		assertEquals("datasrcname",tableConfigBean.getDatasrcname());
	}

	@Test
	public void setDelPolicyTest() {
		tableConfigBean.setDelPolicy("delPolicy");
		assertEquals("delPolicy",tableConfigBean.getDelPolicy());
	}

	@Test
	public void setFilterTest() {
		tableConfigBean.setFilter("filter");
		assertEquals("filter",tableConfigBean.getFilter());
	}

	@Test
	public void setUapTableTest() {
		tableConfigBean.setUapTable("uapTable");
		assertEquals("uapTable",tableConfigBean.getUapTable());
	}

	@Test
	public void setTypeCodeTest() {
		tableConfigBean.setTypeCode("typeCode");
		assertEquals("typeCode",tableConfigBean.getTypeCode());
	}

	@Test
	public void setFieldListTest() {
		tableConfigBean.setFieldList(null);
		assertNull(tableConfigBean.getFieldList());
	}

	@Test
	public void setMultiOrgTest() {
		tableConfigBean.setMultiOrg("multiOrg");
		assertEquals("multiOrg",tableConfigBean.getMultiOrg());
	}

}
