package com.lj.app.core.common.base.entity;


public class UpmFile extends BaseEntity{
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * relateId1
	 */
	private String relateId1;
	
	/**
	 * 
	 */
	private String relateId2;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * optdate
	 */
	private java.util.Date optdate;
	
	 /**
	 * optdateBegin
	 */
	private String  optdateBegin;
	/**
	 * optdateEnd
	 */
	private String optdateEnd;
	/**
	 * operator
	 */
	private Integer operator;
	
	private byte[] content;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setRelateId1(String value) {
		this.relateId1 = value;
	}
	
	public String getRelateId1() {
		return this.relateId1;
	}
	public void setRelateId2(String value) {
		this.relateId2 = value;
	}
	
	public String getRelateId2() {
		return this.relateId2;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setOptdate(java.util.Date value) {
		this.optdate = value;
	}
	
	public java.util.Date getOptdate() {
		return this.optdate;
	}
	public void setOperator(Integer value) {
		this.operator = value;
	}
	
	public Integer getOperator() {
		return this.operator;
	}

	public String getOptdateBegin() {
		return optdateBegin;
	}

	public void setOptdateBegin(String optdateBegin) {
		this.optdateBegin = optdateBegin;
	}

	public String getOptdateEnd() {
		return optdateEnd;
	}

	public void setOptdateEnd(String optdateEnd) {
		this.optdateEnd = optdateEnd;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
}

