package com.lj.app.core.common.base;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class SpringUtilTest {

	@Test
	public void getContextTest() {
		ApplicationContext context = SpringUtil.getSpringFactory();
		assertNotNull(context);
	}

}
