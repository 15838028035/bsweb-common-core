package com.lj.app.core.common.base.config;

/**
 * 
 * <p>应用资源中组织机构数据权限控制方式的配置对象信息</p>
 */

public class DataControlBean {
	private String style;				//代表数据范围控制方式，目前有COMMON,SERVICES方式
	private String orgRoot;				//如果不控制数据范围，需要配置根节点，显示整个组织树
	private String webUrl;				//如果是SERVICES方式，SERVICES的发布地址
	private String servicesName;		//SERVICES名称
	private String reqPara;				//调用SERVICES的请求参数
	private String rspPara;				//调用SERVICES的返回参数
	private String funcClass;			//调用FUNCTION的class
	
	public String getReqPara() {
		return reqPara;
	}
	public void setReqPara(String reqPara) {
		this.reqPara = reqPara;
	}
	
	public String getOrgRoot() {
		return orgRoot;
	}
	public void setOrgRoot(String orgRoot) {
		this.orgRoot = orgRoot;
	}
	
	public String getRspPara() {
		return rspPara;
	}
	public void setRspPara(String rspPara) {
		this.rspPara = rspPara;
	}
	
	public String getServicesName() {
		return servicesName;
	}
	public void setServicesName(String servicesName) {
		this.servicesName = servicesName;
	}
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
	public String getFuncClass() {
		return funcClass;
	}
	public void setFuncClass(String funcClass) {
		this.funcClass = funcClass;
	}
}
