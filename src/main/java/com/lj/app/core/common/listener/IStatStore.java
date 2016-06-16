package com.lj.app.core.common.listener;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @title IStatStore.java
 * @description 在线人数统计的接口，可以使用多种方式实现，如：内存、数据库等
 */
public interface IStatStore {

	/**
	 * 用户登录
	 * 
	 * @param loginName
	 * @param session
	 */
	public void login(String loginName, HttpSession session);

	/**
	 * @description 取消登录状态
	 * @param String
	 * @return
	 */
	public void logoff(String username, HttpSession session);

	/**
	 * @description 返回登录用户及信息
	 * @param
	 * @return 链表里的对象是个数组，数组包含两个元素，０：用户名，１：登录时间
	 */
	public List<Object> getUsers();

	/**
	 * @description 在线用户数量
	 * @param
	 * @return
	 */
	public int getCount();

	/**
	 * 当前session是否需要过期退出处理
	 * 
	 * @return
	 */
	public boolean isNeedInvalidate(String loginName, HttpSession session);

	/**
	 * 获取当前相同用户的登录IP，用来提示这个需要过期处理的session
	 * 
	 * @return
	 */
	public String getLoginIpForTip(String loginName);

	/**
	 * 记录session的客户端IP
	 * 
	 * @param sessionId
	 * @param clientIp
	 */
	public void recordSessionClientIp(String sessionId, String clientIp);
	/**
	 * 判断是否已登录
	 * @param loginName
	 * @return
	 */
	public boolean isLogin(String loginName);
}
