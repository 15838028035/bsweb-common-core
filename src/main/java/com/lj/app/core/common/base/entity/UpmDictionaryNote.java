package com.lj.app.core.common.base.entity;


public class UpmDictionaryNote extends BaseEntity{
	/**
	 *  编号 id
	 */
	private Integer id;
	/**
	 * 类别编码  type_code
	 */
	private String typeCode;

	/**
	 * 描述  type_desc
	 */
	private String typeDesc;

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTypeCode(String value) {
		this.typeCode = value;
	}
	
	public String getTypeCode() {
		return this.typeCode;
	}
	public void setTypeDesc(String value) {
		this.typeDesc = value;
	}
	
	public String getTypeDesc() {
		return this.typeDesc;
	}
}

