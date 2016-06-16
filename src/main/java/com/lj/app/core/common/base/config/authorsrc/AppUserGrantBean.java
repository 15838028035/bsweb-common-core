package com.lj.app.core.common.base.config.authorsrc;

public class AppUserGrantBean {

	private String code;// 权限编码

	private String optype;// 操作类型：具体用户与哪类对象授权，对应options配置中的option

	private String name; // 按钮或页面的title名称

	private String url; // 按钮点击后跳转的url或tab页的iframe对应url

	private String urltype; // iframe的url类型，0-非嵌套页面；1-嵌套页面

	private String isAttr; // 是否是用户的属性，如果是用户属性，则没有单独的用户与对象绑定页面，转移到此属性与权限关联页面

	private String isReverst; // 是否是反向关系

	private String isBatch; // 是否支持批量，包括前台多选及表格批量

	private String batchDesc; // 批量描述

	private String batchTemplatePath; // 批量模板路径

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public String getIsAttr() {
		return isAttr;
	}

	public void setIsAttr(String isAttr) {
		this.isAttr = isAttr;
	}

	public String getIsReverst() {
		return isReverst;
	}

	public void setIsReverst(String isReverst) {
		this.isReverst = isReverst;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public String getBatchDesc() {
		return batchDesc;
	}

	public void setBatchDesc(String batchDesc) {
		this.batchDesc = batchDesc;
	}

	public String getBatchTemplatePath() {
		return batchTemplatePath;
	}

	public void setBatchTemplatePath(String batchTemplatePath) {
		this.batchTemplatePath = batchTemplatePath;
	}

}
