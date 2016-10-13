package com.lj.app.core.common.base.entity;

public class UpmToken extends BaseModel{
	
	/**
	 * ID id
	 */
	private Integer id;
	
	/**
	 * token token_id
	 */
	private String tokenId;
	
	/**
	 * 源资id res_id
	 */
	private String resId;
	
	/**
	 * 从账号 subAcctId
	 */
	private String subAcctId;
	
	/**
	 * 客户端IP client_ip
	 */
	private String clientIp;
	
	/**
	 * 建创时间 create_time
	 */
	private java.util.Date createTime;
	
	/**
	 * 主账号 mainAcct_id
	 */
	private Integer mainAcctId;

	public UpmToken(){
	}


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTokenId(String value) {
		this.tokenId = value;
	}
	
	public String getTokenId() {
		return this.tokenId;
	}
	public void setResId(String value) {
		this.resId = value;
	}
	
	public String getResId() {
		return this.resId;
	}
	public void setSubAcctId(String value) {
		this.subAcctId = value;
	}
	
	public String getSubAcctId() {
		return this.subAcctId;
	}
	public void setClientIp(String value) {
		this.clientIp = value;
	}
	
	public String getClientIp() {
		return this.clientIp;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setMainAcctId(Integer value) {
		this.mainAcctId = value;
	}
	
	public Integer getMainAcctId() {
		return this.mainAcctId;
	}

}

