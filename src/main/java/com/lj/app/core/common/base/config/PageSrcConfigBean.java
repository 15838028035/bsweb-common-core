package com.lj.app.core.common.base.config;

/**
 * 
 * <p>应用资源嵌套页面的配置对象信息</p>
 */

public class PageSrcConfigBean {
	private String linkTitle;			//链接显示的标签
	private String linkUrl;				//链接对应的URL
	private String flag;				//是否为实体内授权嵌套页面，值为1代表是

	private String token;				//嵌套页面验证token存储,用来打开页面，非配置属性
	private String code ; 				//嵌套页面权限控制
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
