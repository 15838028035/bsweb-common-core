package com.lj.app.core.common.base.model;



public class UpmOrganization extends BaseModel{
	
	/**
	 * ID
	 */
	private Integer orgId;
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 组织机构描述
	 */
	private String orgDesc;
	
	/**
	 * 排序编号
	 */
	private Integer sortNo;
	
	/**
	 * 组织机构级别
	 */
	private Integer orgLevel;
	
	/**
	 * 识标
	 */
	private Integer flag;
	
	/**
	 * 父组织机构
	 */
	private UpmOrganization parentOrg;

	public void setOrgId(Integer value) {
		this.orgId = value;
	}
	
	public Integer getOrgId() {
		return this.orgId;
	}
	public void setOrgName(String value) {
		this.orgName = value;
	}
	
	public String getOrgName() {
		return this.orgName;
	}
	public void setOrgDesc(String value) {
		this.orgDesc = value;
	}
	
	public String getOrgDesc() {
		return this.orgDesc;
	}
	public void setSortNo(Integer value) {
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo;
	}
	public void setOrgLevel(Integer value) {
		this.orgLevel = value;
	}
	
	public Integer getOrgLevel() {
		return this.orgLevel;
	}
	public void setFlag(Integer value) {
		this.flag = value;
	}
	
	public Integer getFlag() {
		return this.flag;
	}

	public UpmOrganization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(UpmOrganization parentOrg) {
		this.parentOrg = parentOrg;
	}
	
}

