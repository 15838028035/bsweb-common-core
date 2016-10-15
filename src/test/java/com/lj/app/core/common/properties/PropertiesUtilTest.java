package com.lj.app.core.common.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class PropertiesUtilTest {

	@Test
	public void getPropertyDefaultTest() {
		PropertiesUtil.setProperty("key", "value");
		assertEquals("value",PropertiesUtil.getProperty("key","value1"));
	}
	
	@Test
	public void getPropertyKeyNotFoundDefaultKeyTest() {
		assertEquals("expectedValue",PropertiesUtil.getProperty("keyNotFound","expectedValue"));
	}

	@Test
	public void getPropertyTest() {
		PropertiesUtil.setProperty("key", "value");
		assertEquals("value",PropertiesUtil.getProperty("key"));
		
		Properties properties = PropertiesUtil.getProperty();
		assertNotNull(properties);
	}
	
	@Test
	public void getPropertyKeyNotFoundTest() {
		assertNull(PropertiesUtil.getProperty("keyNotFound"));
	}

	@Test
	public void clearTest() {
		Properties properties = new Properties();
		assertNull(properties.get("key"));
		
		properties.put("key", "value");
		assertEquals("value",properties.get("key"));
		
		properties.clear();
		assertNull(properties.get("key"));
	}

	@Test
	public void getRequiredPropertyTest() {
		PropertiesUtil.setProperty("key", "value");
		assertEquals("value",PropertiesUtil.getRequiredProperty("key"));
	}
	
	@Test(expected=java.lang.IllegalStateException.class)
	public void getRequiredPropertyNotFoundTest() {
		assertNull(PropertiesUtil.getRequiredProperty("keyNotFound"));
	}
	
	@Test
	public void getValueTest() {
		PropertiesUtil.setProperty("key", "value");
		assertNull(PropertiesUtil.getValue("key","value"));
	}

	@Test
	public void setPropertyIntTest() {
		PropertiesUtil.setProperty("intKey", 1);
		assertTrue(1==PropertiesUtil.getInteger("intKey").intValue());
	}

	@Test
	public void setPropertyLongTest() {
		Long longKey = 2L;
		PropertiesUtil.setProperty("longKey", longKey);
		assertEquals(longKey,PropertiesUtil.getLong("longKey"));
	}

	@Test
	public void setPropertyFloatTest() {
		Float floatKey = 3.0f;
		PropertiesUtil.setProperty("floatKey", floatKey);
		assertEquals(floatKey,PropertiesUtil.getFloat("floatKey"));
	}

	@Test
	public void setPropertyDoubleTest() {
		Double doubleKey = 3.0d;
		PropertiesUtil.setProperty("doubleKey", doubleKey);
		assertEquals(doubleKey,PropertiesUtil.getDouble("doubleKey"));
	}

	@Test
	public void setPropertyBooleanTest() {
		boolean booleanKey = true;
		PropertiesUtil.setProperty("booleanKey", booleanKey);
		assertEquals(true,PropertiesUtil.getBoolean("booleanKey"));
	}

	@Test
	public void containsTest() {
		PropertiesUtil.setProperty("key", "value");
		assertFalse(PropertiesUtil.contains("key"));
		assertTrue(PropertiesUtil.contains("value"));
		assertFalse(PropertiesUtil.contains("keyNotFound"));
	}

	@Test
	public void containsKeyTest() {
		PropertiesUtil.setProperty("key", "value");
		assertTrue(PropertiesUtil.containsKey("key"));
		assertFalse(PropertiesUtil.containsKey("keyNotFound"));
	}

	@Test
	public void containsValueTest() {
		PropertiesUtil.setProperty("key", "value");
		assertTrue(PropertiesUtil.containsValue("value"));
		assertFalse(PropertiesUtil.containsValue("valueNotFound"));
	}

	@Test
	public void getIntegerTest() {
		PropertiesUtil.setProperty("intKey", 1);
		assertTrue(1==PropertiesUtil.getInteger("intKey").intValue());
	}

	@Test
	public void getIntTest() {
		PropertiesUtil.setProperty("intKey", 1);
		assertTrue(1==PropertiesUtil.getInt("intKey",0));
	}

	@Test
	public void getRequiredIntTest() {
		PropertiesUtil.setProperty("intKey", 1);
		assertTrue(1==PropertiesUtil.getRequiredInt("intKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredIntKeyNotFoundTest() {
		PropertiesUtil.getRequiredInt("keyNotFound");
	}

	@Test
	public void getLongTest() {
		Long longKey = 2L;
		PropertiesUtil.setProperty("longKey", longKey);
		assertTrue(2.0==PropertiesUtil.getLong("longKey"));
	}

	@Test
	public void getLongDefaultTest() {
		Long longKey = 3L;
		PropertiesUtil.setProperty("longKey", longKey);
		assertTrue(3.0==PropertiesUtil.getLong("longKey",4));
	}

	@Test
	public void getRequiredLongTest() {
		Long longKey = 3L;
		PropertiesUtil.setProperty("longKey", longKey);
		assertTrue(3.0==PropertiesUtil.getRequiredLong("longKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredLongKeyNotFoundTest() {
		PropertiesUtil.getRequiredLong("keyNotFound");
	}

	@Test
	public void getBooleanTest() {
		boolean booleanKey= true;
		PropertiesUtil.setProperty("booleanKey", booleanKey);
		assertTrue(PropertiesUtil.getBoolean("booleanKey"));
	}

	@Test
	public void getBooleanDefaultTest() {
		assertTrue(PropertiesUtil.getBoolean("booleanKeyNotFound",true));
		assertFalse(PropertiesUtil.getBoolean("booleanKeyNotFound2",false));
	}

	@Test
	public void getRequiredBooleanTest() {
		boolean booleanKey= true;
		PropertiesUtil.setProperty("booleanKey", booleanKey);
		assertTrue(PropertiesUtil.getRequiredBoolean("booleanKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredBooleanKeyNotFoundTest() {
		PropertiesUtil.getRequiredBoolean("keyNotFound");
	}

	@Test
	public void getFloatTest() {
		Float floatKey = 3F;
		PropertiesUtil.setProperty("floatKey", floatKey);
		assertTrue(3.0==PropertiesUtil.getFloat("floatKey"));
	}

	@Test
	public void getFloatDefaultTest() {
		assertTrue(4==PropertiesUtil.getFloat("floatKeyNotFound",4));
	}

	@Test
	public void getRequiredFloatTest() {
		Float floatKey = 3F;
		PropertiesUtil.setProperty("floatKey", floatKey);
		assertTrue(3.0==PropertiesUtil.getFloat("floatKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredFloatKeyNotFoundTest() {
		PropertiesUtil.getRequiredFloat("floatKeyNotFound");
	}

	@Test
	public void getDoubleTest() {
		Double doubleKey = 3d;
		PropertiesUtil.setProperty("doubleKey", doubleKey);
		assertTrue(3.0==PropertiesUtil.getDouble("doubleKey").doubleValue());
	}

	@Test
	public void getDoubleDefaultTest() {
		Double doubleKey = 3d;
		PropertiesUtil.setProperty("doubleKey", doubleKey);
		assertTrue(3.0==PropertiesUtil.getDouble("doubleKey",4));
		assertTrue(5==PropertiesUtil.getDouble("doubleKeyNotFound",5));
	}

	@Test
	public void getRequiredDoubleTest() {
		PropertiesUtil.setProperty("doubleKey", 3.0);
		assertTrue(3.0==PropertiesUtil.getRequiredDouble("doubleKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredDoubleKeyNotFoundTest() {
		PropertiesUtil.setProperty("doubleKey", "3.0");
		assertNull(PropertiesUtil.getRequiredDouble("doubleKeyNotFound"));
	}

	@Test
	public void setPropertyTest() {
		PropertiesUtil.setProperty("key", "value");
		assertEquals("value",PropertiesUtil.getProperty("key","value1"));
	}

	@Test
	public void getPropertyTrimTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("value",PropertiesUtil.getPropertyTrim("key"));
	}
	
	@Test
	public void getPropertyTrimValueNullTest() {
		assertEquals("",PropertiesUtil.getPropertyTrim("keyNotFound"));
	}
	
	@Test
	public void getPropertyTrimDefaultTest() {
		PropertiesUtil.setProperty("key", "value ");
		assertEquals("value",PropertiesUtil.getPropertyTrim("key", " value2"));
		assertEquals("expectedValue",PropertiesUtil.getPropertyTrim("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getPropertyTrimDefaultValueNullTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("value2",PropertiesUtil.getPropertyTrim("key2", " value2"));
		assertEquals("expectedValue",PropertiesUtil.getPropertyTrim("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getPropertyLowerTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("value",PropertiesUtil.getPropertyLower("key"));
	}
	
	@Test
	public void getPropertyLowerNullTest() {
		assertEquals("",PropertiesUtil.getPropertyLower("keyNotFound"));
	}
	
	@Test
	public void getPropertyLowerDefaultTest() {
		PropertiesUtil.setProperty("key", "value2 ");
		assertEquals("value2",PropertiesUtil.getPropertyLower("key", " value2"));
		assertEquals("expectedvalue",PropertiesUtil.getPropertyLower("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getPropertyLowerDefaultValueNullTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("value2",PropertiesUtil.getPropertyLower("key2", " value2"));
		assertEquals("expectedvalue",PropertiesUtil.getPropertyLower("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getPropertyUpperTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("VALUE",PropertiesUtil.getPropertyUpper("key"));
	}
	
	@Test
	public void getPropertyUpperNullTest() {
		assertEquals("",PropertiesUtil.getPropertyUpper("keyNotFound"));
	}
	
	@Test
	public void getPropertyUpperDefaultTest() {
		PropertiesUtil.setProperty("key", "value ");
		assertEquals("VALUE",PropertiesUtil.getPropertyUpper("key", " value2"));
		assertEquals("EXPECTEDVALUE",PropertiesUtil.getPropertyUpper("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getPropertyUpperDefaultValueNullTest() {
		PropertiesUtil.setProperty("key", " value ");
		assertEquals("VALUE2",PropertiesUtil.getPropertyUpper("key2", " value2"));
		assertEquals("EXPECTEDVALUE",PropertiesUtil.getPropertyUpper("keyNotFound","expectedValue"));
	}
	
	@Test
	public void getBooleanTrueOrFalseTest() {
		PropertiesUtil.setProperty("key", true);
		assertTrue(PropertiesUtil.getBooleanTrueOrFalse("key"));
	}
	
	@Test
	public void getBooleanTrueOrFalseDefaultTest() {
		PropertiesUtil.setProperty("key", true);
		assertTrue(PropertiesUtil.getBooleanTrueOrFalse("key", true));
		assertFalse(PropertiesUtil.getBooleanTrueOrFalse("keyNotFound",false));
	}
	
	@Test
	public void getMessageTest() {
		String msg = PropertiesUtil.getMessage("fileName", "key");
		assertEquals("key",msg);
	}
	
	@Test
	public void getPropertyArrayTest() {
		PropertiesUtil.setProperty("keyA", "a,b");
		assertEquals("a",PropertiesUtil.getPropertyArray("keyA")[0]);
		assertEquals("b",PropertiesUtil.getPropertyArray("keyA")[1]);
	}
	
	@Test
	public void getPropertyListTest() {
		PropertiesUtil.setProperty("keyA", "a,b");
		assertEquals("a",PropertiesUtil.getPropertyList("keyA").get(0));
		assertEquals("b",PropertiesUtil.getPropertyList("keyA").get(1));
	}
}
