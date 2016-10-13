package com.lj.app.core.common.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.base.entity.UpmOrganization;

public class UpmOrganizationTest {

	private UpmOrganization upmOrganization;
	
	@Before
	public void setUp() {
		upmOrganization = new UpmOrganization();
	}
	
	@Test
	public void setOrgIdTest() {
		upmOrganization.setOrgId(1);
		assertTrue(1==upmOrganization.getOrgId());
	}

	@Test
	public void setOrgNameTest() {
		upmOrganization.setOrgName("orgName");
		assertEquals("orgName",upmOrganization.getOrgName());
	}

	@Test
	public void setOrgDescTest() {
		upmOrganization.setOrgDesc("orgDesc");
		assertEquals("orgDesc",upmOrganization.getOrgDesc());
	}

	@Test
	public void setSortNoTest() {
		upmOrganization.setSortNo(1);
		assertTrue(1==upmOrganization.getSortNo());
	}

	@Test
	public void testSetOrgLevel() {
		upmOrganization.setOrgLevel(1);
		assertTrue(1==upmOrganization.getOrgLevel());
	}

	@Test
	public void setFlagTest() {
		upmOrganization.setFlag(1);
		assertTrue(1==upmOrganization.getFlag());
	}

	@Test
	public void testSetParentOrg() {
		UpmOrganization parentOrg = new UpmOrganization();
		parentOrg.setOrgId(0);
		
		upmOrganization.setParentOrg(parentOrg);
		assertTrue(0==upmOrganization.getParentOrg().getOrgId());
	}

}
