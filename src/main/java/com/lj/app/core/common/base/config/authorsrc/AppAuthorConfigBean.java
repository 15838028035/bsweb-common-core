package com.lj.app.core.common.base.config.authorsrc;

import java.util.List;

public class AppAuthorConfigBean {

	private AppUserGrantConfigBean appUserGrantConfigBean;

	private List<AppAuthorOption> options;

	public AppUserGrantConfigBean getAppUserGrantConfigBean() {
		return appUserGrantConfigBean;
	}

	public void setAppUserGrantConfigBean(AppUserGrantConfigBean appUserGrantConfigBean) {
		this.appUserGrantConfigBean = appUserGrantConfigBean;
	}

	public List<AppAuthorOption> getOptions() {
		return options;
	}

	public void setOptions(List<AppAuthorOption> options) {
		this.options = options;
	}

}