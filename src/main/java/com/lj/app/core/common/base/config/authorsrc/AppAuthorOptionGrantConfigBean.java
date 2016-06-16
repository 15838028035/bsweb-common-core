package com.lj.app.core.common.base.config.authorsrc;

import java.util.List;

public class AppAuthorOptionGrantConfigBean {

	private String code;// 权限编码

	private String type;// 类型：button-各种权限操作是按钮；tab-各种权限操作是一个页面的多tab

	private String name; // 按钮或页面的title名称

	private String url; // 按钮点击后跳转的url或tab页的iframe对应url

	private String urltype; // iframe的url类型，0-非嵌套页面；1-嵌套页面

	private String isBatch; // 是否支持批量 type=tab时有效或控制excel导入

	private List<AppAuthorOptionGrant> appAuthorOptionGrants;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<AppAuthorOptionGrant> getAppAuthorOptionGrants() {
		return appAuthorOptionGrants;
	}

	public void setAppAuthorOptionGrants(List<AppAuthorOptionGrant> appAuthorOptionGrants) {
		this.appAuthorOptionGrants = appAuthorOptionGrants;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

}
