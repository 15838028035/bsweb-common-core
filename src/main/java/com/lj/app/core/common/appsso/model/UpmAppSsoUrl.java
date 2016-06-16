package com.lj.app.core.common.appsso.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class UpmAppSsoUrl {
	private Long appId;
	private String ssoName;
	private String ssoUrl;

	public void setAppId(Long value) {
		this.appId = value;
	}

	public Long getAppId() {
		return this.appId;
	}

	public void setSsoName(String value) {
		this.ssoName = value;
	}

	public String getSsoName() {
		return this.ssoName;
	}

	public void setSsoUrl(String value) {
		this.ssoUrl = value;
	}

	public String getSsoUrl() {
		return this.ssoUrl;
	}

	public String toString() {
		return new ToStringBuilder(this).append("AppId", getAppId())
				.append("SsoName", getSsoName()).append("SsoUrl", getSsoUrl())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getAppId()).append(getSsoName())
				.append(getSsoUrl()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof UpmAppSsoUrl))
			return false;
		if (this == obj)
			return true;
		UpmAppSsoUrl other = (UpmAppSsoUrl) obj;
		return new EqualsBuilder().append(getAppId(), other.getAppId())
				.append(getSsoName(), other.getSsoName())
				.append(getSsoUrl(), other.getSsoUrl()).isEquals();
	}
}