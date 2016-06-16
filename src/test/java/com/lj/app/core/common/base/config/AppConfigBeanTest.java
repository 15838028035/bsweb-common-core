package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AppConfigBeanTest {

	private AppConfigBean appConfigBean;
	
	@Before
	public void setUp() {
		appConfigBean = new AppConfigBean();
	}

	@Test
	public void setBatchfuncTest() {
		appConfigBean.setBatchfunc("setBatchfunc");
		assertEquals("setBatchfunc",appConfigBean.getBatchfunc());
	}

	@Test
	public void setAcctTypeTest() {
		appConfigBean.setAcctType("setAcctType");
		assertEquals("setBatchfunc",appConfigBean.getAcctType());
	}


	@Test
	public void setBatchQueryModeTest() {
		appConfigBean.setBatchQueryMode("setBatchQueryMode");
		assertEquals("setBatchQueryMode",appConfigBean.getBatchQueryMode());
	}

	@Test
	public void setSingleQueryModeTest() {
		appConfigBean.setSingleQueryMode("setSingleQueryMode");
		assertEquals("setSingleQueryMode",appConfigBean.getSingleQueryMode());
	}

	@Test
	public void setDebugTest() {
		appConfigBean.setDebug("setDebug");
		assertEquals("setDebug",appConfigBean.getDebug());
	}

	@Test
	public void setEncrytTest() {
		appConfigBean.setEncryt("setEncryt");
		assertEquals("setEncryt",appConfigBean.getEncryt());
	}

	@Test
	public void setSyncModeTest() {
		appConfigBean.setSyncMode("setSyncMode");
		assertEquals("setSyncMode",appConfigBean.getSyncMode());
	}

	@Test
	public void setDefaultDataSrcTest() {
		appConfigBean.setDefaultDataSrc("setDefaultDataSrc");
		assertEquals("setDefaultDataSrc",appConfigBean.getDefaultDataSrc());
	}

	@Test
	public void setAcctBeanTest() {
		appConfigBean.setAcctBean("setAcctBean");
		assertEquals("setAcctBean",appConfigBean.getAcctBean());
	}

	@Test
	public void setAcctServiceTest() {
		appConfigBean.setAcctService("setAcctService");
		assertEquals("setAcctService",appConfigBean.getAcctService());
	}


	@Test
	public void setSoapMapTest() {
		appConfigBean.setSoapMap(null);
		assertEquals(null,appConfigBean.getSoapMap());
	}


	@Test
	public void setPageListTest() {
		appConfigBean.setPageList(null);
		assertEquals(null,appConfigBean.getPageList());
	}


	@Test
	public void setDataControlBeanTest() {
		appConfigBean.setDataControlBean(null);
		assertEquals(null,appConfigBean.getDataControlBean());
	}

	@Test
	public void setTableConfigMapTest() {
		appConfigBean.setTableConfigMap(null);
		assertEquals(null,appConfigBean.getTableConfigMap());
	}

	@Test
	public void setBatchAcctServiceTest() {
		appConfigBean.setBatchAcctService("setBatchAcctService");
		assertEquals("setBatchAcctService",appConfigBean.getBatchAcctService());
	}


	@Test
	public void setPermissionConfigBeanTest() {
		appConfigBean.setPermissionConfigBean(null);
		assertEquals(null,appConfigBean.getPermissionConfigBean());
	}


	@Test
	public void setSubAppTest() {
		appConfigBean.setSubApp(null);
		assertEquals(null,appConfigBean.getSubApp());
	}


	@Test
	public void setParentAppTest() {
		appConfigBean.setParentApp("setParentApp");
		assertEquals("setParentApp",appConfigBean.getParentApp());
	}

	@Test
	public void setSubAttrIdTest() {
		appConfigBean.setParentApp("setSubAttrId");
		assertEquals("setSubAttrId",appConfigBean.getSubAttrId());
	}

	@Test
	public void setBrotherAppTest() {
		appConfigBean.setBrotherApp("setBrotherApp");
		assertEquals("setBrotherApp",appConfigBean.getBrotherApp());
	}

	@Test
	public void setAcctExtTest() {
		appConfigBean.setBrotherApp("setAcctExt");
		assertEquals("setAcctExt",appConfigBean.getAcctExt());
	}

	@Test
	public void setSyncDealModeTest() {
		appConfigBean.setSyncDealMode("setSyncDealMode");
		assertEquals("setSyncDealMode",appConfigBean.getSyncDealMode());
	}

	@Test
	public void setCallWebServiceTest() {
		appConfigBean.setCallWebService("setCallWebService");
		assertEquals("setCallWebService",appConfigBean.getCallWebService());
	}

	@Test
	public void setAuthorRelServiceGetterTest() {
		appConfigBean.setAuthorRelServiceGetter("setAuthorRelServiceGetter");
		assertEquals("setAuthorRelServiceGetter",appConfigBean.getAuthorRelServiceGetter());
	}

	@Test
	public void setAuthorUpdateServiceGetterTest() {
		appConfigBean.setAuthorUpdateServiceGetter("setAuthorUpdateServiceGetter");
		assertEquals("setAuthorUpdateServiceGetter",appConfigBean.getAuthorUpdateServiceGetter());
	}

	@Test
	public void setAuthorServiceTest() {
		appConfigBean.setAuthorUpdateServiceGetter("setAuthorService");
		assertEquals("setAuthorService",appConfigBean.getAuthorService());
	}

	@Test
	public void setBatchAuthorServiceTest() {
		appConfigBean.setBatchAuthorService("setBatchAuthorService");
		assertEquals("setBatchAuthorService",appConfigBean.getBatchAuthorService());
	}

	@Test
	public void setTimeDominantTest() {
		appConfigBean.setTimeDominant("setTimeDominant");
		assertEquals("setTimeDominant",appConfigBean.getTimeDominant());
	}

}
