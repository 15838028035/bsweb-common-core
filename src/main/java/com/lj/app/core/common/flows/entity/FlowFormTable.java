package com.lj.app.core.common.flows.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

/**
*FlowFormTable
*/
public class FlowFormTable extends BaseEntity{
	
	/**
	 * ID  id
	 */
	private java.lang.Integer id;
	
	/**
	 * 名称  name
	 */
	private String name;
	
	/**
	 * 显示名称  display_name
	 */
	private String displayName;
	
	/**
	 * 类型  type
	 */
	private String type;
	
	/**
	 * HTML  origin_html
	 */
	private String originHtml;
	
	/**
	 * HTML  parse_html
	 */
	private String parseHtml;
	
	private int fieldNum;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginHtml() {
		return originHtml;
	}

	public void setOriginHtml(String originHtml) {
		this.originHtml = originHtml;
	}

	public String getParseHtml() {
		return parseHtml;
	}

	public void setParseHtml(String parseHtml) {
		this.parseHtml = parseHtml;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}
	
}

