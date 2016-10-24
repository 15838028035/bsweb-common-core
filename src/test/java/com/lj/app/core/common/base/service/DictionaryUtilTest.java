package com.lj.app.core.common.base.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.lj.app.core.common.base.entity.UpmDictionary;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class DictionaryUtilTest extends AbstractBaseSpringTransactionTestCase{

	@Test
	public void getInstanceTest() {
		DictionaryUtil dictionaryUtil1 = DictionaryUtil.getInstance();
		DictionaryUtil dictionaryUtil2 = DictionaryUtil.getInstance();
		assertEquals(dictionaryUtil1,dictionaryUtil2);
	}

	@Test
	public void typeAndDateCodeToNameTest() throws Exception {
		String typeName= DictionaryUtil.typeAndDateCodeToName("powerState", "powerState");
		assertNotNull(typeName);
	}

	@Test
	public void findByTypeCodeTest()  throws Exception {
		UpmDictionary upmDictionary = DictionaryUtil.findDicData("powerState", "1");
		assertNotNull(upmDictionary);
	}

	@Test
	public void findDicDataTest()  throws Exception {
		List<UpmDictionary> upmDictionaryList = DictionaryUtil.findByTypeCode("powerState");
		assertNotNull(upmDictionaryList);
	}

	@Test
	public void refreshDataNotStaticTest()  throws Exception {
		DictionaryUtil.getInstance().refreshDataNotStatic();
	}

	@Test
	public void initDataTest()  throws Exception {
		DictionaryUtil.initData();
	}

	@Test
	public void refreshDataTest() {
		DictionaryUtil.refreshData();
	}

}
