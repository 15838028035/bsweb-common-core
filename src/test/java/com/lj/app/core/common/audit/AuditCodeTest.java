package com.lj.app.core.common.audit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AuditCodeTest {
	
	@Before
	public void setUp() {
		AuditCode auditCode = new AuditCode();
		AuditCode.SysCfg sysConfig = new AuditCode.SysCfg();
	}

	@Test
	public void sysCfgCodeTest() {
		assertEquals("1-UAP-20016",AuditCode.SysCfg.createRole);
		assertEquals("1-UAP-20017",AuditCode.SysCfg.updateRole);
		assertEquals("1-UAP-20018",AuditCode.SysCfg.deleteRole);
		assertEquals("1-UAP-20019",AuditCode.SysCfg.queryRole);
		
		assertEquals("1-UAP-20413",AuditCode.SysCfg.UAP_DICTIONARY_NOTE_ADD);
		assertEquals("1-UAP-20414",AuditCode.SysCfg.UAP_DICTIONARY_NOTE_MODIFY);
		assertEquals("1-UAP-20415",AuditCode.SysCfg.UAP_DICTIONARY_NOTE_DEL);
		assertEquals("1-UAP-20416",AuditCode.SysCfg.UAP_DICTIONARY_ADD);
		assertEquals("1-UAP-20417",AuditCode.SysCfg.UAP_DICTIONARY_MODIFY);
		assertEquals("1-UAP-20418",AuditCode.SysCfg.UAP_DICTIONARY_DEL);
		
		assertEquals("1-UAP-20419",AuditCode.SysCfg.UAP_PERMISSION_ADD);
		assertEquals("1-UAP-20420",AuditCode.SysCfg.UAP_PERMISSION_MODIFY);
		assertEquals("1-UAP-20421",AuditCode.SysCfg.UAP_PERMISSION_DEL);
		assertEquals("1-UAP-20422",AuditCode.SysCfg.UAP_PERMISSION_CANCEL);
		assertEquals("1-UAP-20423",AuditCode.SysCfg.UAP_PERMISSION_STAR);
	}
	
	@Test
	public void tokenTest() {
		assertEquals("1-UAP-20595",AuditCode.tokenCreate);
		assertEquals("1-UAP-20596",AuditCode.tokenCheck);
	}

}
