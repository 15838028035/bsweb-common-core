package com.lj.app.core.common.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lj.app.bsweb.upm.user.model.UpmUser;
import com.lj.app.core.common.util.SessionCode;

/**
 * @title :MemStat.java
 * @description 依托内存实现在线用户统计功能
 */
public class MemStat implements IStatStore {

	private static Map<String, HttpSession> userMap = null;
	private static Map<String, Map<String, String>> validateUserMap = null;
	private static Map<String, String> validateSessionIpMap = null;
	
	static {
		userMap = new HashMap<String, HttpSession>();
		validateUserMap = new HashMap<String, Map<String, String>>();
		validateSessionIpMap = new HashMap<String, String>();
	}

	public void login(String username, HttpSession session) {
		Object obj = userMap.get(username);
		if (obj != null) {
			userMap.remove(username);
			HttpSession oldSession = (HttpSession) obj;
			// oldSession.invalidate();
			Map<String, String> invalidate = validateUserMap.get(username);
			if (invalidate == null) {
				invalidate = new HashMap<String, String>();
			}
			if (!invalidate.containsKey(oldSession.getId())) {
				invalidate.put(oldSession.getId(), "");
			}
			validateUserMap.put(username, invalidate);
		}
		userMap.put(username, session);
	}
	
	public boolean isLogin(String loginName){
		HttpSession newSession = userMap.get(loginName);
		if(null != newSession){
			Calendar calendar1=Calendar.getInstance();  
			Calendar calendar2=Calendar.getInstance();
			UpmUser upmUser = (UpmUser)newSession.getAttribute(SessionCode.MAIN_ACCT);
			try {
				calendar1.setTime(new Date());
				calendar2.setTime(upmUser.getLastLoginTime());
				if((calendar1.getTimeInMillis()-calendar2.getTimeInMillis())>1800000){
					return false;
				}else{
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}else{
			return false;
		}
	}

	public void logoff(String username, HttpSession session) {
		Object obj = userMap.get(username);
		if (obj != null) {
			HttpSession oldSession = (HttpSession) obj;
			System.out.println(oldSession.getId());
			if (session.getId().equals(oldSession.getId())) {
				userMap.remove(username);
			}
			Map<String, String> needInValidate = validateUserMap.get(username);
			if (needInValidate != null) {
				needInValidate.remove(session.getId());
				if (needInValidate == null || needInValidate.size() == 0) {
					validateUserMap.remove(username);
				}
			}
			validateSessionIpMap.remove(session.getId());
			session.invalidate();
		}
	}

	public List<Object> getUsers() {
		List<Object> list = new LinkedList<Object>();
		String user = null;
		for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
			user = (String) it.next();
			list.add(new Object[] { user, userMap.get(user) });
		}
		return list;
	}

	public int getCount() {
		return userMap.size();
	}

	public boolean isNeedInvalidate(String loginName, HttpSession session) {
		Map<String, String> needInValidate = validateUserMap.get(loginName);
		return (needInValidate != null && needInValidate.containsKey(session
				.getId()));
	}

	public String getLoginIpForTip(String loginName) {
		HttpSession newSession = userMap.get(loginName);
		String newClientIp = "";
		if (newSession != null) {
			newClientIp = validateSessionIpMap.get(newSession.getId());
		}
		return newClientIp;
	}

	public void recordSessionClientIp(String sessionId, String clientIp) {
		validateSessionIpMap.put(sessionId, clientIp);
	}
}
