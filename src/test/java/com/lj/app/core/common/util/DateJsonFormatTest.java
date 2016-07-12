package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.Test;

import com.lj.app.core.common.base.model.BaseModel;

public class DateJsonFormatTest {

	@Test
	public void processArrayValueTest() {
		//TODO:implments me
	}

	/**
	 * 解析map对象测试
	 */
	@Test
	public void processObjectValueMapTest() {
		
		Map<String,String> mapObj = new HashMap<String,String>();
		mapObj.put("key", "value");
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(java.util.Date.class,df);
		
		JSONObject all = JSONObject.fromObject(mapObj,cfg);
		String jsonString = all.toString();
		
		String expteced = "{\"key\":\"value\"}";
		assertEquals(expteced, jsonString);
	}
	
	/**
	 * 解析map对象测试
	 */
	@Test
	public void processObjectValueMapJsonConfigNullTest() {
		
		Map<String,String> mapObj = new HashMap<String,String>();
		mapObj.put("key", "value");
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(BadTestClass.class,df);
		
		JSONObject all = JSONObject.fromObject(mapObj,cfg);
		String jsonString = all.toString();
		
		String expteced = "{\"key\":\"value\"}";
		assertEquals(expteced, jsonString);
	}
	
	/**
	 * 解析PO对象测试
	 */
	@Test
	public void processObjectValuePoObjectTest() {
		
		BaseModel baseModel =new BaseModel();
		baseModel.setAppId("appId");
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(java.util.Date.class,df);
		
		JSONObject all = JSONObject.fromObject(baseModel,cfg);
		String jsonString = all.toString();
		
		assertTrue(jsonString.contains("appId"));
	}
	
	/**
	 * 解析PO对象测试
	 */
	@Test
	public void processObjectValuePoObjectConfigNullTest() {
		
		BaseModel baseModel =new BaseModel();
		baseModel.setAppId("appId");
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		
		cfg.registerJsonValueProcessor(BadTestClass.class,df);
		
		JSONObject all = JSONObject.fromObject(baseModel,cfg);
		String jsonString = all.toString();
		
		assertTrue(jsonString.contains("appId"));
	}
	
	/**
	 * 解析日期对象测试
	 */
	@Test
	public void processObjectValueDateTest() {
		
		DateTest dateTest =new DateTest();
		dateTest.setDate1(DateUtil.formatDate("2016-06-20 10:00:00", "yyyy-MM-dd HH:mm:ss"));
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(java.util.Date.class,df);
		
		JSONObject all = JSONObject.fromObject(dateTest,cfg);
		String jsonString = all.toString();
		
		System.out.println("jsonString:"+jsonString);
		String expteced = "{\"date1\":\"2016-06-20 10:00:00\"}";
		assertEquals(expteced, jsonString);
	}

	
	/**
	 * 解析日期对象测试
	 */
	@Test
	public void processObjectValueDateConfigNullTest() {
		
		DateTest dateTest =new DateTest();
		dateTest.setDate1(DateUtil.formatDate("2016-06-20 10:00:00", "yyyy-MM-dd HH:mm:ss"));
		
		JsonConfig cfg = new JsonConfig();		
		DateJsonFormat df = new DateJsonFormat();
		cfg.registerJsonValueProcessor(BadTestClass.class,df);
		
		JSONObject all = JSONObject.fromObject(dateTest,cfg);
		String jsonString = all.toString();
		
		System.out.println("jsonString:"+jsonString);
		String expteced = "{\"date1\":{\"date\":20,\"day\":1,\"hours\":10,\"minutes\":0,\"month\":5," +
				"\"seconds\":0,\"time\":1466388000000,\"timezoneOffset\":-480,\"year\":116}}";
		assertEquals(expteced, jsonString);
	}
	
	
}
