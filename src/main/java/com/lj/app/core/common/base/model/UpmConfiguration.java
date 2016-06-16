package com.lj.app.core.common.base.model;


public class UpmConfiguration extends BaseModel{
	
	/**
	 * ID
	 */
	private Integer configId;
	
	/**
	 * 置配项KEY
	 */
	private String cfgKey;
	
	/**
	 * 置配项值
	 */
	private String cfgValue;
	
	/**
	 * 置配项描述
	 */
	private String cfgDesc;
	


	public void setConfigId(Integer value) {
		this.configId = value;
	}
	
	public Integer getConfigId() {
		return this.configId;
	}
	public void setCfgKey(String value) {
		this.cfgKey = value;
	}
	
	public String getCfgKey() {
		return this.cfgKey;
	}
	public void setCfgValue(String value) {
		this.cfgValue = value;
	}
	
	public String getCfgValue() {
		return this.cfgValue;
	}
	public void setCfgDesc(String value) {
		this.cfgDesc = value;
	}
	
	public String getCfgDesc() {
		return this.cfgDesc;
	}
	
}

