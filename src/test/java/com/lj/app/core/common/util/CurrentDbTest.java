package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CurrentDbTest {

	private CurrentDb currentDb;
	
	@Before
	public void setUp()  {
		currentDb = new CurrentDb();
	}
	@Test
	public void setDriverClassTest() {
		currentDb.setDriverClass(CurrentDb.ORACLE);
		assertEquals(CurrentDb.ORACLE,currentDb.getCurrentDb());
		currentDb.setDriverClass(CurrentDb.SYBASE);
		assertEquals(CurrentDb.SYBASE,currentDb.getCurrentDb());
		currentDb.setDriverClass(CurrentDb.DB2);
		assertEquals(CurrentDb.DB2,currentDb.getCurrentDb());
		currentDb.setDriverClass(CurrentDb.SQLSERVER);
		assertEquals(CurrentDb.SQLSERVER,currentDb.getCurrentDb());
		currentDb.setDriverClass(CurrentDb.MYSQL);
		assertEquals(CurrentDb.MYSQL,currentDb.getCurrentDb());
	}

}
