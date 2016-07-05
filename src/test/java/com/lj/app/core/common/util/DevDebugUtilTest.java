package com.lj.app.core.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DevDebugUtilTest {

	@Test
	public void isDevModeTest() {
		assertTrue(DevDebugUtil.isDevModel());
		assertFalse(DevDebugUtil.isProModel());
	}

}
