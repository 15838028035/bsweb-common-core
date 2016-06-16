package com.lj.app.core.common.base.config.authorsrc;

import java.util.List;

public class AppUserGrantConfigBean {

	private String code;// 权限编码

	private String type;// 类型：button-各种权限操作是按钮；tab-各种权限操作是一个页面的多tab

	private String name; // 按钮或页面的title名称

	private String url; // 按钮点击后跳转的url或tab页的iframe对应url

	private String urltype; // iframe的url类型，0-嵌套页面；1-非嵌套页面

	private String isBatch; // 是否支持批量，在type=tab时生效

	private List<AppUserGrantBean> appUserGrants;// 用户授权的对象

	private List<AppUserExtButton> appUserExtButtons;// 扩展按钮

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

	public List<AppUserGrantBean> getAppUserGrants() {
		return appUserGrants;
	}

	public void setAppUserGrants(List<AppUserGrantBean> appUserGrants) {
		this.appUserGrants = appUserGrants;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public List<AppUserExtButton> getAppUserExtButtons() {
		return appUserExtButtons;
	}

	public void setAppUserExtButtons(List<AppUserExtButton> appUserExtButtons) {
		this.appUserExtButtons = appUserExtButtons;
	}

}
