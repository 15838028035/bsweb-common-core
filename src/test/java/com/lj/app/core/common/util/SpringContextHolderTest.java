package com.lj.app.core.common.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class SpringContextHolderTest extends AbstractBaseSpringTransactionTestCase {
	
	private static ApplicationContext applicationContext;
	
	@BeforeClass
	public static  void setupContext() {
	}

	@Test
	public void setApplicationContextTest() {
		
	}

	@Test
	public void getApplicationContextTest() {
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNotNull(applicationContext);
	}

	@Test
	public void getBeanByBeanNameTest() {
		SpringContextHolder springContextHolder = SpringContextHolder.getBean("springContextHolder");
		assertNotNull(springContextHolder);
	}

	@Test
	public void getBeanClassOfTTest() {
		SpringContextHolder springContextHolder =(SpringContextHolder) SpringContextHolder.getBean(SpringContextHolder.class);
		assertNotNull(springContextHolder);
	}

	@Test
	@Ignore
	public void cleanApplicationContextTest() {
		SpringContextHolder springContextHolder = SpringContextHolder.getBean("springContextHolder");
		assertNotNull(springContextHolder);
		SpringContextHolder.cleanApplicationContext();
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNull(applicationContext);
	}

}
