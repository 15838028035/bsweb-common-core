package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.web.Struts2Utils;

public class IdentifyingCodeUtilTest extends AbstractBaseSpringTransactionTestCase {

	private IdentifyingCodeUtil identifyingCodeUtil;
	
	@Before
	public void setUP(){
		identifyingCodeUtil = new IdentifyingCodeUtil();
	}
	
	@Test
	public void getIdentifyingCodeTest() {
		String code = identifyingCodeUtil.getIdentifyingCode(); 
		assertNotNull(code);
		
		String randCode = (String) Struts2Utils.getSession().getAttribute("rand");
		assertEquals(code,randCode);
	}

}
