package com.lj.app.core.common.base.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.lj.app.core.common.base.model.UpmDictionary;

public class DictionaryUtilTest {

	@Test
	public void getInstanceTest() {
		DictionaryUtil dictionaryUtil1 = DictionaryUtil.getInstance();
		DictionaryUtil dictionaryUtil2 = DictionaryUtil.getInstance();
		assertEquals(dictionaryUtil1,dictionaryUtil2);
	}

	@Test
	public void typeAndDateCodeToNameTest() {
		String typeName= DictionaryUtil.typeAndDateCodeToName("typeCode", "dataCode");
		assertNotNull(typeName);
	}

	@Test
	public void findByTypeCodeTest() {
		UpmDictionary upmDictionary = DictionaryUtil.findDicData("typeCode", "dataCode");
		assertNotNull(upmDictionary);
	}

	@Test
	public void findDicDataTest() {
		List<UpmDictionary> upmDictionaryList = DictionaryUtil.findByTypeCode("typeCode");
		assertNotNull(upmDictionaryList);
	}

	@Test
	public void refreshDataNotStaticTest() {
		DictionaryUtil.getInstance().refreshDataNotStatic();
	}

	@Test
	public void initDataTest() {
		DictionaryUtil.initData();
	}

	@Test
	public void refreshDataTest() {
		DictionaryUtil.refreshData();
	}

}
