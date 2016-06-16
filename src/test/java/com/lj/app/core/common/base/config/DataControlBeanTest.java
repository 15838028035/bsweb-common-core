package com.lj.app.core.common.base.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DataControlBeanTest {

	private DataControlBean dataControlBean;
	
	@Before
	public void setUp() {
		dataControlBean = new DataControlBean();
	}

	@Test
	public void setReqParaTest() {
		dataControlBean.setReqPara("reqPar");
		assertEquals("reqPar",dataControlBean.getReqPara());
	}

	@Test
	public void setOrgRootTest() {
		dataControlBean.setOrgRoot("orgRoot");
		assertEquals("orgRoot",dataControlBean.getOrgRoot());
	}

	@Test
	public void setRspParaTest() {
		dataControlBean.setRspPara("resPara");
		assertEquals("resPara",dataControlBean.getRspPara());
	}

	@Test
	public void setServicesNameTest() {
		dataControlBean.setServicesName("servicesName");
		assertEquals("servicesName",dataControlBean.getServicesName());
	}

	@Test
	public void setStyleTest() {
		dataControlBean.setStyle("style");
		assertEquals("style",dataControlBean.getStyle());
	}

	@Test
	public void setWebUrlTest() {
		dataControlBean.setWebUrl("webUrl");
		assertEquals("webUrl",dataControlBean.getWebUrl());
	}

	@Test
	public void setFuncClassTest() {
		dataControlBean.setFuncClass("funcClass");
		assertEquals("funcClass",dataControlBean.getFuncClass());
	}

}
