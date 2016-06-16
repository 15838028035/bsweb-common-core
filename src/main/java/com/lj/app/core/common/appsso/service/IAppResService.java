package com.lj.app.core.common.appsso.service;

import java.util.List;

import com.lj.app.core.common.appsso.model.UpmAppSsoUrl;


/**
 * @title :IAppResService.java
 * @description : 应用资源各类操作接口类
 */
public interface IAppResService {
	
	/**
	 * @description: 单点登录系统UPM
	 * @return:String
	 */
	public String accessUpmSysSSO();
		

    /**
     * @description: 获取登录应用的url,金库信息
     * @param mainAcctId 主帐号ID
     * @param url url地址
     * @param acctSeq ID
     * @param appAcctId 从长号ID
     * @param appAcctName 从帐号名字
     * @param appId 应用ID
     * @param appCode 应用编码
     * @param ipAddress ip地址
     * @param macAddress mac地址
     * @param cpuSerial cpu信息
     * @param hostName 本地名称
     * @param hostAccount 本地帐户
     * @return：ssoURl地址
     */
    public String getSSOAppUrl(int mainAcctId,String url, String acctSeq , String appAcctId,String appAcctName ,String appId,String appCode,String ipAddress,String macAddress,String cpuSerial,String hostName,String hostAccount,String bankApprove,String bankFlag);
    
    /**
     * @description: 获取登录应用的url
     * @param mainAcctId 主帐号ID
     * @param url url地址
     * @param acctSeq ID
     * @param appAcctId 从长号ID
     * @param appAcctName 从帐号名字
     * @param appId 应用ID
     * @param appCode 应用编码
     * @param ipAddress ip地址
     * @param macAddress mac地址
     * @param cpuSerial cpu信息
     * @param hostName 本地名称
     * @param hostAccount 本地帐户
     * @return：
     */
    public String getSSOAppUrl(int mainAcctId,String url, String acctSeq , String appAcctId,String appAcctName ,String appId,String appCode,String ipAddress,String macAddress,String cpuSerial,String hostName,String hostAccount);
	
	/**
	 * 获得ssoUrl列表
	 * 
	 * @param appCode
	 *            编码
	 * @param appId
	 *            应用id
	 * @param loginAcct
	 *            登录id
	 * @return
	 */
	public List<UpmAppSsoUrl> getSsoUrlList(String appCode, String appId, String loginAcct) ;
	/**
	 * 根据appCode获得子应用名称
	 * 
	 * @param appCode
	 * @param subAppCode
	 * @return
	 */
	public String getAppName(String appCode, String subAppCode) ;

}
