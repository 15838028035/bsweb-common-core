package com.lj.app.core.common.audit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AuditOperatorIdTest {

	@Before
	public void setUp(){
		AuditOperatorId auditOperatorId = new AuditOperatorId();
		AuditOperatorId.Login login = new AuditOperatorId.Login();
	}
	
	@Test
	public void loginOpterIdTest() {
		assertEquals("1-UAP-10000",AuditOperatorId.Login.LOGIN);
		assertEquals("1-UAP-10027",AuditOperatorId.Login.LOGOUT);
		assertEquals("1-UAP-10058",AuditOperatorId.Login.LOCK);
	}

}
