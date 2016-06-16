package com.lj.app.core.common.base.config;

import java.util.List;

/**
 * 
 * <p>应用资源查询接口视图的配置对象定义</p>
 */

public class TableConfigBean {
	private String name;						//应用视图名称
	private String sql;							//应用SQL语句，与name属性二选一
	private String datasrcname;					//数据源名称,与一期不同，一期一个应用配置一个数据源，二期每个查询接口定义一个数据源
	private String type;						//应用视图类型，主表分为ORG,USER,ROLE,USERROLE,USERORG几类,其他为自定义
	private String filter;						//查询对应表时的过滤器，可空
	private String delPolicy;					//删除策略(Y/N)：值Y代表应用删除帐号为物理删除，N代表逻辑删除，
												//物理删除的从原表删除，移入到历史表。逻辑删除只在原表修改状态。不配置是默认为Y
	private String multiOrg;					//帐号是否归属多个组织机构，为Y代表可以归属多个，为N代表只能归属一个组织机构
	private String uapTable;					//对应到4A的表名，可空。只有对需要同步的维表，有此属性。按原有应用的表结构同步即可
	private String typeCode;					//如果对方的维表比较规则，可以入到4A的数据字典表，这里指定入到字典表的TYPE_CODE	
	private List<FieldConfigBean> fieldList;	//对应的字段列表
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getType() {
		return type;
	}

	public void setDatasrcname(String datasrcname) {
		this.datasrcname = datasrcname;
	}
	
	public String getDatasrcname() {
		return datasrcname;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDelPolicy() {
		return delPolicy;
	}

	public void setDelPolicy(String delPolicy) {
		this.delPolicy = delPolicy;
	}
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public String getUapTable() {
		return uapTable;
	}

	public void setUapTable(String uapTable) {
		this.uapTable = uapTable;
	}
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public List<FieldConfigBean> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldConfigBean> fieldList) {
		this.fieldList = fieldList;
	}
	
	public String getMultiOrg() {
		return multiOrg;
	}

	public void setMultiOrg(String multiOrg) {
		this.multiOrg = multiOrg;
	}

}
