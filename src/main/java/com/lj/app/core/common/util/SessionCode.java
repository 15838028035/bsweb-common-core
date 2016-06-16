package com.lj.app.core.common.util;

public class SessionCode {
	
	/**
	 * Session 中存放登录名称的Session Code 
	 */
	public final static String LOGIN_NAME = "loginName";
	/**
	 * Session 中存放登录名称的Session Code 
	 */
	public final static String USER_NAME = "userName";
	
	/**
	 * Session 中存放登主帐号的Session Code 
	 */
	public final static String MAIN_ACCT = "mainAcct";

	
	/**
	 * Session 中存放，主帐号ID
	 */
	public final static String MAIN_ACCT_ID = "mainAcctId" ;
	
	
	//----------------- Client Info -----------------------//
	
    /**
     * Session中存放，菜单列表 CODE=perminssionList
     */
    public final static String PERMINSSION_LIST = "perminssionList";
    
	/**
	 * Request 中存放，错误信息
	 */
	public final static String ERROR_MESSAGE = "errorMessage";
	
	/**
     * Request 中存放，警告信息
     */
	public final static String WARN_MESSAGE = "warnMessage";
	
	/**
     * Request 中存放，普通提示信息
     */
	public final static String INFO_MESSAGE = "infoMessage";
	
	/**
     * Request 中存放，普通提示信息
     */
    public final static String SUCCESS_MESSAGE = "successMessage";
    
    /**
	 * Session 中存放，真实请求服务ip地址
	 */
	public final static String CLIENT_INFO_REQUEST_ADDRESS = "requestAddress";	
	
	/**
	 * Session 中存放，ip地址，逗号分隔，4A侧专用(审计+内部访问使用)
	 */
	public final static String CLIENT_INFO_IP_ADDRESS = "ipAddress";
	
	/**
	 * Session 中存放，ip地址列表
	 */
	public final static String CLIENT_INFO_IP_ADDRESS_LIST = "ipAddressList";	
	
	/**
	 * Session 中存放，Mac地址
	 */
	public final static String CLIENT_INFO_MAC_ADDRESS = "macAddress";
	
	/**
	 * Session 中存放，Mac地址列表
	 */
	public final static String CLIENT_INFO_MAC_ADDRESS_LIST = "macAddressList";	
	
	/**
	 * Session中存放，cpu序列号
	 */
	public final static String CLIENT_INFO_CPU_SERIAL = "cpuSerial";
	
	/**
	 * Session中存放，服务器ip地址
	 */
	public final static String SERVER_INFO_IP_ADDRESS = "serverIp";
	

	/**
	 * Session中存放，服务器端口
	 */
	public final static String SERVER_INFO_PORT = "serverPort";
	
	
	/**
     * Session中存放，计算机名称
     */
	public final static String CLIENT_INFO_HOST_NAME = "hostName";
	
	/**
	 * Session中存放，登录到当前计算机的用户名称
	 */
	public final static String CLIENT_INFO_HOST_ACCOUNT = "hostAccount";
	
	public final static String SSO_LOGIN_URL = "SSO_LOGIN_URL";
	
	
	/**
	 * Session 中存放，输错的密码次数
	 */
	public final static String PASSWORD_CHECK_COUNT = "passwordCheckCount";	
	
}
