package com.lj.app.core.common.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import  com.lj.app.core.common.base.service.BaseService;

public abstract class AbstractBaseActionTest {

	@Test
	public void reloadTest(){
		assertEquals("reload",BaseActionTest.RELOAD);
	}
	
	@Test
	public void redirectTest(){
		assertEquals("redirect",BaseActionTest.REDIRECT);
	}
	
	@Test
	public void inputTest(){
		assertEquals("input",BaseActionTest.INPUT);
	}
	
	@Test
	public void editTest(){
		assertEquals("edit",BaseActionTest.EDIT);
	}
	
	@Test
	public void saveTest(){
		assertEquals("save",BaseActionTest.SAVE);
	}
	
	@Test
	public void listTest(){
		assertEquals("list",BaseActionTest.LIST);
	}
	
	@Test
	public void pageSizeTest(){
		assertEquals(20,BaseActionTest.PAGESIZE);
	}
	
	@Test
	public void createSuccessTest(){
		assertEquals("保存成功",BaseActionTest.CREATE_SUCCESS);
	}
	
	@Test
	public void updateSuccessTest(){
		assertEquals("修改成功",BaseActionTest.UPDATE_SUCCESS);
	}
	
	@Test
	public void deleteSuccessTest(){
		assertEquals("删除成功",BaseActionTest.DELETE_SUCCESS);
	}
	
	@Test
	public void createFailureTest(){
		assertEquals("保存失败",BaseActionTest.CREATE_FAILURE);
	}
	
	@Test
	public void updateFailureTest(){
		assertEquals("修改失败",BaseActionTest.UPDATE_FAILURE);
	}
	
	@Test
	public void deleteFailureTest(){
		assertEquals("删除失败",BaseActionTest.DELETE_FAILURE);
	}
	
	@Test
	public void createByTest(){
		assertEquals("createBy",BaseActionTest.CREATE_BY);
	}
	
	private class Class1{
		
	}
	
	private class BaseActionTest<Class1> extends AbstractBaseAction{

		private Class1 Class1;
		
		public Class1 getModel() {
			return Class1;
		}

		public String list() throws Exception {
			return null;
		}

		public String input() throws Exception {
			return null;
		}

		public String save() throws Exception {
			return null;
		}

		public String delete() throws Exception {
			return null;
		}

		protected void prepareModel() throws Exception {
			
		}
		
		public   BaseService<Class1> getBaseService(){
			return null;
		}
	}
}
