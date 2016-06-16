package com.lj.app.core.common.base.config;

/**
 * 
 * <p>应用资源权限结构的配置对象信息</p>
 */

public class PermissionConfigBean {
	private String type;				//取值:tree,other
	private String roottype;			//取值：single,multi
	private String rootvalue;			//根节点的值：当为multi时多根用逗号隔开
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoottype() {
		return roottype;
	}
	public void setRoottype(String roottype) {
		this.roottype = roottype;
	}
	public String getRootvalue() {
		return rootvalue;
	}
	public void setRootvalue(String rootvalue) {
		this.rootvalue = rootvalue;
	}
}
