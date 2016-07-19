package com.lj.app.core.common.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lj.app.core.common.web.AbstractBaseAction;

public class ClassUtilTest {

	@Test
	public void newInstanceClassTest() {
		StringUtil stringUtil = (StringUtil)ClassUtil.newInstance(StringUtil.class);
		assertNotNull(stringUtil);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newInstanceClassExceptionTest() {
		ClassUtil.newInstance(AbstractBaseAction.class);
	}

	@Test
	public void newInstanceClassNameTest() {
		StringUtil stringUtil = (StringUtil)ClassUtil.newInstance("com.lj.app.core.common.util.StringUtil");
		assertNotNull(stringUtil);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newInstanceClassNameExceptionTest() {
		ClassUtil.newInstance("bad class name");
	}

	@Test
	public void getDefaultClassLoaderTest() {
		ClassLoader classLoader = ClassUtil.getDefaultClassLoader();
		System.out.println(classLoader);
		assertNotNull(classLoader);
	}

}
