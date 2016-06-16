package com.lj.app.core.common.util;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AjaxResultTest {

	@Test
	public void setOpResultTest() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setOpResult("success");
		assertEquals("success", ajaxResult.getOpResult());
	}

}
