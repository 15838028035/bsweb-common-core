package com.lj.app.core.common.base.entity;

import com.lj.app.core.common.base.entity.BaseModel;

/**
 * 用户信息
 *
 */
public class UpmUser extends BaseModel{
	/**
	 * ID
	 */
	private java.lang.Integer id;
	
	/**
	 * 登陆账号
	 */
	private String loginNo;
	
	/**
	 * 登陆密码
	 */
	private String pwd;
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 组织机构描述
	 */
	private String orgDesc;
	/**
	 * 组织机构编码
	 */
	private String userGroupCode;
	
	private int userGroupAndUserRelId;//用户组关联id
	
	/**
	 * 最后登录时间
	 */
	private java.util.Date lastLoginTime;
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public String getLoginNo() {
		return loginNo;
	}
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getUserGroupAndUserRelId() {
		return userGroupAndUserRelId;
	}

	public void setUserGroupAndUserRelId(int userGroupAndUserRelId) {
		this.userGroupAndUserRelId = userGroupAndUserRelId;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public String getUserGroupCode() {
		return userGroupCode;
	}
	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
}
