package com.lj.app.core.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBUtil {
	private static Log logger = LogFactory.getLog(DBUtil.class);
 	private static final String driverClass = "com.mysql.jdbc.Driver";
 	private static final String url = "jdbc:mysql://localhost:3306/upmcrm?useUnicode=true&characterEncoding=UTF-8";
 	private static final String userName = "root";
 	private static final String password = "root";
 	
 	private static Connection currentConnection;
 	
 	static {
 		currentConnection = getConnection();
 	}
 	

 	/**
 	 * 获取数据库连接
 	 * @param driverClass		驱动类
 	 * @param url				url地址
 	 * @param userName			用户名
 	 * @param password			密码
 	 * @return Conection		Connection对象
 	 */
	public static Connection getConnection(String driverClass, String url, String userName, String password){
		try {
			logger.debug("dirverClass=" + driverClass);
			logger.debug("url= " +url);
			logger.debug("userName= " + userName);
			logger.debug("password=" + password);
			logger.debug("start to get connection ");

			Class.forName(driverClass);
			Connection con = DriverManager.getConnection(url, userName, password);
			logger.debug("<!--get connection successfully-->");
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.debug("<!--get driverClass error-->");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("<!--get connection error-->");
		}
		return null;
	}
	
	public static  Connection getConnection() {
		return getConnection(driverClass,url, userName, password );
	}

	
	/**
	 * @Title: closeConnection
	 * @Description: 关闭数据库连接对象
	 * @param 数据库连接对象
	 */
	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			logger.error("<!--closed connection error-->");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("<!--closed connection error-->");
				e.printStackTrace();
			}
		}
	}
	
	public static void closePrepStmt(PreparedStatement prepStmt) {
		try {
			if (prepStmt != null)
				prepStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<!--closed connection error-->");
		}
	}
	
	/**
	 * 根据查询语句，查询数据库中列的值
	 * @param conn			连接对象
	 * @param querySql		查询sql
	 * @param column		查询列名
	 * @return				查询列表的值
	 */
	public static String getCountValue(Connection conn, String querySql,String column) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(querySql);
			ResultSet rs = prepStmt.executeQuery();
			String result = "0";
			while (rs.next()) {
				result = rs.getString(column);

			}
			DBUtil.closeResultSet(rs);
			DBUtil.closePrepStmt(prepStmt);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";// 异常情况反馈-1
		}
	}
	
	public static Connection getCurrentConnection() {
		return currentConnection;
	}

}
