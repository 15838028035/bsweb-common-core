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
	
	/**
	 * app菜单权限列表
	 */
	public final static String APP_MENU_PERMISSION_LIST ="appMenuPermissionList";
	
	
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
	 * Session中存放，服务器ip地址
	 */
	public final static String SERVER_INFO_IP_ADDRESS = "serverIp";
	
	/**
     * Session中存放，计算机名称
     */
	public final static String CLIENT_INFO_HOST_NAME = "hostName";
	
	public final static String SSO_LOGIN_URL = "SSO_LOGIN_URL";
	
	/**
	 * spring profiles配置
	 */
	public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
	
}
