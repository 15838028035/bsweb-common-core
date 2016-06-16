package com.lj.app.core.common.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class SpringContextHolderTest {
	
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
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNotNull(applicationContext);
		SpringContextHolder springContextHolder = (SpringContextHolder)SpringContextHolder.getBean("springContextHolder");
		assertNotNull(springContextHolder);
	}

	@Test
	public void getBeanClassOfTTest() {
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNotNull(applicationContext);
		SpringContextHolder springContextHolder = (SpringContextHolder)SpringContextHolder.getBean(SpringContextHolder.class);
		assertNotNull(springContextHolder);
	}

	@Test
	public void cleanApplicationContextTest() {
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNotNull(applicationContext);
		SpringContextHolder springContextHolder = (SpringContextHolder)SpringContextHolder.getBean(SpringContextHolder.class);
		assertNotNull(springContextHolder);
		SpringContextHolder.cleanApplicationContext();
		applicationContext = SpringContextHolder.getApplicationContext();
		assertNull(applicationContext);
	}

}
