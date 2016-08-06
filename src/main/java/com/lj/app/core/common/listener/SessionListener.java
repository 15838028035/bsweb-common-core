package com.lj.app.core.common.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.base.model.UpmUser;
import com.lj.app.core.common.util.SessionCode;

/**
 * @title :SessionListener.java
 * @description :session监听器
 */
public class SessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	private final static Log log = LogFactory.getLog(SessionListener.class);

	private static HashMap<String, String> users = new HashMap<String, String>();

	/**
	 * 定义监听的session属性名.
	 */
	public final static String LOGIN = SessionCode.MAIN_ACCT;

	/**
	 * @description: session创建
	 * @param HttpSessionEvent
	 * @return
	 */
	public void sessionCreated(HttpSessionEvent se) {
		log.info("session create : " + se.getSession().getId());
	}

	/**
	 * @description 加入session时的监听方法
	 * @param HttpSessionBindingEvent
	 * @return
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();

		if (LOGIN.equals(name)) {
			if(null == event.getSession().getAttribute("StaticPwdCheckForLogin")){
				StoreFactory.getStore().login(
						((UpmUser) event.getValue()).getLoginNo(),
						event.getSession());
	
				users.put(event.getSession().getId(), ((UpmUser) event
						.getValue()).getLoginNo());
				log.info("somebody login===="
						+ ((UpmUser) event.getValue()).getLoginNo());
			}
		}
	}

	/**
	 * @description session失效时的监听方法
	 * @param HttpSessionBindingEvent
	 * @return
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		if (se.getSession() != null) {
			if (se.getSession().getAttribute(LOGIN) != null) {
				if (((UpmUser) se.getSession().getAttribute(LOGIN)) != null) {
					String name = ((UpmUser) se.getSession().getAttribute(
							LOGIN)).getLoginNo();
					log.info("somebody logout====" + name);
					if (name != null) {
						StoreFactory.getStore().logoff(name,se.getSession());
					}
				}
			}

			users.remove(se.getSession().getId());
		}
	}

	/**
	 * @description session覆盖时的监听方法
	 * @param HttpSessionBindingEvent
	 * @return
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		log.debug("Attribute Replaced ,event value = " + event.getName());

		if (LOGIN.equals(event.getName())) {
			HttpSession session = event.getSession();
				UpmUser user = (UpmUser) session.getAttribute(LOGIN);
			if(null != user && users.get(session.getId())!=null){
				if (!users.get(session.getId()).equals(user.getLoginNo())) {
					event.getSession().invalidate();
				}
			}
		}
	}

}
