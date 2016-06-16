package com.lj.app.core.common.base.config;

/**
 * 
 * <p>应用资源服务接口的配置对象定义</p>
 */
public class SoapUrlConfigBean {
	private String name;				//接口对应服务名
	private String authen;				//调用服务时是否需要身份认证
	private String clientIp;			//接口报文是否需要客户端IP
	private String urlAddr;				//服务发布的对应的URL
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthen() {
		return authen;
	}
	public void setAuthen(String authen) {
		this.authen = authen;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getUrlAddr() {
		return urlAddr;
	}
	public void setUrlAddr(String urlAddr) {
		this.urlAddr = urlAddr;
	}
	
}
