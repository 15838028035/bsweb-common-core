package com.lj.app.core.common.base.config.authorsrc;

import java.util.List;

public class AppAuthorOption {

	private String code;// 权限编码

	private String type;// 操作类型：具体用户与哪类对象授权，对应options配置中的option

	private String name; // 按钮或页面的title名称

	private String url; // 按钮点击后跳转的url或tab页的iframe对应url

	private String urltype; // iframe的url类型，0-非嵌套页面；1-嵌套页面

	private String isBatch; // 是否支持批量

	private List<AppAuthorOptionOperate> operateList; // 授权实体的操作按钮

	private AppAuthorOptionGrantConfigBean appAuthorOptionGrantConfigBean; // 对象权限授权

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

	public AppAuthorOptionGrantConfigBean getAppAuthorOptionGrantConfigBean() {
		return appAuthorOptionGrantConfigBean;
	}

	public void setAppAuthorOptionGrantConfigBean(AppAuthorOptionGrantConfigBean appAuthorOptionGrantConfigBean) {
		this.appAuthorOptionGrantConfigBean = appAuthorOptionGrantConfigBean;
	}

	public List<AppAuthorOptionOperate> getOperateList() {
		return operateList;
	}

	public void setOperateList(List<AppAuthorOptionOperate> operateList) {
		this.operateList = operateList;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

}
