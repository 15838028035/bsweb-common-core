package com.lj.app.core.common.flows.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
*FlowFormField
*/
public class FlowFormField extends BaseEntity{
	
	public static final String FLOW = "1";
	
	/**
	 * ID  id
	 */
	private java.lang.Integer id;
	
	/**
	 * 字段名称  name
	 */
	private String name;
	
	/**
	 * plugins  plugins
	 */
	private String plugins;
	
	/**
	 * 标题  title
	 */
	private String title;
	
	/**
	 * 类型  type
	 */
	private String type;
	
	/**
	 * 流程  flow
	 */
	private String flow;
	
	/**
	 * 表名称  table_name
	 */
	private String tableName;
	
	/**
	 * 表单ID  form_id
	 */
	private java.lang.Integer formId;
	


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setPlugins(String value) {
		this.plugins = value;
	}
	
	public String getPlugins() {
		return this.plugins;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return this.type;
	}
	public void setFlow(String value) {
		this.flow = value;
	}
	
	public String getFlow() {
		return this.flow;
	}
	public void setTableName(String value) {
		this.tableName = value;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	public void setFormId(java.lang.Integer value) {
		this.formId = value;
	}
	
	public java.lang.Integer getFormId() {
		return this.formId;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Name",getName())
			.append("Plugins",getPlugins())
			.append("Title",getTitle())
			.append("Type",getType())
			.append("Flow",getFlow())
			.append("TableName",getTableName())
			.append("FormId",getFormId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getName())
			.append(getPlugins())
			.append(getTitle())
			.append(getType())
			.append(getFlow())
			.append(getTableName())
			.append(getFormId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FlowFormField == false) return false;
		if(this == obj) return true;
		FlowFormField other = (FlowFormField)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getName(),other.getName())
			.append(getPlugins(),other.getPlugins())
			.append(getTitle(),other.getTitle())
			.append(getType(),other.getType())
			.append(getFlow(),other.getFlow())
			.append(getTableName(),other.getTableName())
			.append(getFormId(),other.getFormId())
			.isEquals();
	}
}

