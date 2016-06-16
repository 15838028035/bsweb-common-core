package com.lj.app.core.common.util;

import java.sql.Connection;

import org.junit.Test;

public class DBUtilTest {

	private static final String driverClass = "com.mysql.jdbc.Driver";
 	private static final String url = "jdbc:mysql://localhost:3306/upmcrm?useUnicode=true&characterEncoding=UTF-8";
 	private static final String userName = "root";
 	private static final String password = "root";
 	
	@Test
	public void testGetConnectionStringStringStringString() {
	}

	@Test
	public void getConnectionTest() {
		DBUtil.getConnection(driverClass, url, userName, password);
	}

	@Test
	public void closeConnectionTest() {
		Connection con = DBUtil.getConnection(driverClass, url, userName, password);
		DBUtil.closeConnection(con);
	}

	@Test
	public void closePrepStmtTest() {
	}

	@Test
	public void testCloseResultSet() {
	}

	@Test
	public void testGetCountValue() {
	}

	@Test
	public void testGetCurrentConnection() {
	}

}
