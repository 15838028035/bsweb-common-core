package com.lj.app.core.common.security;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CMSecurityContext {

	private Long mainAcctId;
	
	private String loginName;
	
	private Set<String> urls = new HashSet();
	
	private Set<String> codes = new HashSet();
	
	private String loginTime;
	
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}


	/**是否有url访问权限
	 * @param requestUrl
	 * @return
	 */
	public boolean hasUrlPermission(String requestUrl) {
		for (String url : urls) {
			if(requestUrl.indexOf(url) != -1) {
				return true;
			}
		}
		
		return false;
	}

	/**是否有显示的权限
	 * @param requestCode
	 * @return
	 */
	public boolean hasDisplayPermission(String requestCode) {
		return codes.contains(requestCode);
	}
	
	public Set<String> getCodes() {
		return codes;
	}

	public void setCodes(Set<String> codes) {
		this.codes = codes;
	}

	public Long getMainAcctId() {
		return mainAcctId;
	}

	public void setMainAcctId(Long mainAcctId) {
		this.mainAcctId = mainAcctId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public Set<String> getUrls() {
		return urls;
	}

}
