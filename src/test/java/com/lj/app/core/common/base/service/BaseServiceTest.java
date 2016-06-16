package com.lj.app.core.common.base.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class BaseServiceTest extends AbstractBaseSpringTransactionTestCase{

	@Autowired
	private BaseService baseService;
	
	private MockTestClass mockTestClass;
	
	@Test
	@Ignore
	public void insertObjectObjectTest() {
		baseService.insertObject(mockTestClass);
	}

	@Test
	@Ignore
	public void insertObjectStringObjectTest() {
		baseService.insertObject("insert", mockTestClass);
	}

	@Test
	@Ignore
	public void updateObjectObject() {
		baseService.updateObject(mockTestClass);
	}

	@Test
	@Ignore
	public void updateObjectStringObjectTest() {
		baseService.updateObject("update",mockTestClass);
	}

	@Test
	@Ignore
	public void findObjectTest() {
		assertNotNull("Find obj must not be null",baseService.findObject("find", mockTestClass));
	}
	
	@Test
	@Ignore
	public void findObjectNotExistsTest() {
		assertNull("Find obj must not  null",baseService.findObject("findNotExists", mockTestClass));
	}

	@Test
	@Ignore
	public void testGetInfoByKeyStringObject() {
	}

	@Test
	@Ignore
	public void getInfoByKeyObjectTest() {
		assertNotNull("Get obj must not be null",baseService.getInfoByKey(mockTestClass));
	}
	
	@Test
	@Ignore
	public void getInfoByKeyObjectNotExistsTest() {
		assertNull("Get obj must not be null",baseService.getInfoByKey("findNotExists",mockTestClass));
	}

	@Test
	@Ignore
	public void queryObjectTest() {
		assertNotNull("query obj must not be null",baseService.queryForObject("query",mockTestClass));
	}
	
	@Test
	@Ignore
	public void queryObjectNotExistsTest() {
		assertNull("query obj must not be null",baseService.queryForObject("queryNotExists",mockTestClass));
	}

	@Test
	@Ignore
	public void testFindBaseModeListObject() {
	}

	@Test
	@Ignore
	public void testFindBaseModeListStringObject() {
	}

	@Test
	@Ignore
	public void testFindBaseModePageListObject() {
	}

	@Test
	@Ignore
	public void testFindBaseModePageListStringObject() {
	}

	@Test
	@Ignore
	public void testDeleteObject() {
	}

	@Test
	@Ignore
	public void testDeleteStringObject() {
	}

	@Test
	@Ignore
	public void testGetSqlMapNameSpace() {
	}

	@Test
	@Ignore
	public void testQueryForListString() {
	}

	@Test
	@Ignore
	public void testQueryForListStringObject() {
	}

	@Test
	@Ignore
	public void testQueryForListStringIntInt() {
	}

	@Test
	@Ignore
	public void testQueryForListStringObjectIntInt() {
	}

	@Test
	@Ignore
	public void testFindPageListPageOfTMapOfStringObjectString() {
	}

	@Test
	@Ignore
	public void testFindPageListPageOfTMapOfStringObject() {
	}

	@Test
	@Ignore
	public void testCountObject() {
	}

}
