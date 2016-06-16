package com.lj.app.core.common.base.config.authorsrc;

public class AppAuthorOptionOperate {

	private String code;// 权限编码

	private String name; // 按钮名称

	private String urltype; // iframe的url类型，0-非嵌套页面；1-嵌套页面

	private String url; // 按钮点击后跳转的url或tab页的iframe对应url

	private String type;

	private String isBatch; // 是否支持批量

	private String batchDesc; // 批量描述

	private String batchTemplatePath; // 批量模板路径

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
