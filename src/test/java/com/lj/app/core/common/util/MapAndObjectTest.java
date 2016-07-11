package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.base.model.BaseModel;

public class MapAndObjectTest {

	private Map<Object,Object> map = new HashMap<Object,Object>();
	private BaseModel baseModel = new BaseModel();
	
	@Before
	public void setUp() {
		map.put("appId", "from map");
		map.put(123, 456);

		baseModel.setCreateBy(1);
		baseModel.setCreateDate("2016-06-10");
	}
	
	@Test
	public void mapAndObjectTest() {
		 MapAndObject m = new MapAndObject(map,baseModel);
		    assertEquals("from map",m.get("appId"));

		    map.put("appId", null);
		    assertNull(m.get("appId"));

		    baseModel.setAppId(null);
		    assertEquals(null ,m.get("appId"));

		    assertNull(m.get("notexistmethod"));
	}

	@Test
	public void testGetMap() {
		//TODO:test me 
	}

	@Test
	public void testGetBean() {
		//TODO:test me 
	}

	@Test
	public void testGet() {
		//TODO:test me 
	}

	@Test
	public void testGetFromMapOrBean() {
		//TODO:test me 
	}

	@Test
	public void testClear() {
		//TODO:test me 
	}

	@Test
	public void testContainsKey() {
		//TODO:test me 
	}

	@Test
	public void testContainsValue() {
		//TODO:test me 
	}

	@Test
	public void testEntrySet() {
		//TODO:test me 
	}

	@Test
	public void testIsEmpty() {
		//TODO:test me 
	}

	@Test
	public void testKeySet() {
		//TODO:test me 
	}

	@Test
	public void testPut() {
		//TODO:test me 
	}

	@Test
	public void testPutAll() {
		//TODO:test me 
	}

	@Test
	public void testRemove() {
		//TODO:test me 
	}

	@Test
	public void testSize() {
		//TODO:test me 
	}

	@Test
	public void testValues() {
		//TODO:test me 
	}

	@Test
	public void testWithNull() {
	    MapAndObject m = new MapAndObject(null,null);
	    assertEquals(null,m.get("1"));

	    m = new MapAndObject(map,null);
	    assertEquals("from map",m.get("appId"));

	    m = new MapAndObject(null,baseModel);
	    assertEquals("2016-06-10",m.get("createDate"));
	}

	
	@Test
	public void setCreateDateTest() {
	    MapAndObject m = new MapAndObject(map,baseModel);
	    assertEquals("CreateDate from BaseModel.java","2016-06-10",m.get("createDate"));
	}
	
}
