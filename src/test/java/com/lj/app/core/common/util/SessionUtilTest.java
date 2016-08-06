package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lj.app.core.common.base.model.UpmUser;
import com.lj.app.core.common.security.CMSecurityContext;
import com.lj.app.core.common.security.SecurityConstants;
import com.lj.app.core.common.web.Struts2Utils;

public class SessionUtilTest extends AbstractBaseSpringTransactionTestCase {

	@Test
	public void getUserTest() {
		assertEquals("admin",SessionUtil.getUser().getLoginNo());
	}

	@Test
	public void setuserTest() {
		UpmUser upmUser  = new UpmUser();
		upmUser.setLoginNo("adminTest");
		SessionUtil.setuser(upmUser);
		assertEquals("adminTest",SessionUtil.getUser().getLoginNo());
		upmUser.setLoginNo("admin");
		SessionUtil.setuser(upmUser);
		assertEquals("admin",SessionUtil.getUser().getLoginNo());
	}

	@Test
	public void getMainAcctId() {
		assertTrue(SessionUtil.getMainAcctId()>0);
	}

	@Test
	public void getUserNameTest() {
		assertEquals("admin",SessionUtil.getUserName());
	}

	@Test
	public void getSessionAttributeTest() {
		CMSecurityContext securityContext = (CMSecurityContext) Struts2Utils.getSessionAttribute(SecurityConstants.SECURITY_CONTEXT);
		assertNotNull(securityContext);
	}

	@Test
	public void getSessionMapTest() {
		assertEquals("admin",(String)SessionUtil.getSessionMap().get("loginName"));
	}

	@Test
	public void getHttpSessionTest() {
		assertNotNull(SessionUtil.getHttpSession());
	}

	@Test
	public void setHttpSession() {
		SessionUtil.setHttpSession(Struts2Utils.getSession());
		assertNotNull(SessionUtil.getHttpSession());
	}

}
