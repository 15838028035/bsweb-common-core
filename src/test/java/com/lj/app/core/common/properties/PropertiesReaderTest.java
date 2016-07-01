package com.lj.app.core.common.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class PropertiesReaderTest {

	@Test
	public void getBaseConfigValueTest() {
		PropertiesReader.setProperty("key", "value");
		assertNull(PropertiesReader.getBaseConfigValue("key"));
		assertNull(PropertiesReader.getBaseConfigValue("keyNotFound"));
	}
	
	@Test
	public void getBaseConfigValueDefaultTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getBaseConfigValue("key","value"));
	}

	@Test
	public void getValueTest() {
		PropertiesReader.setProperty("key", "value");
		assertNull(PropertiesReader.getValue("key","value"));
	}

	@Test
	public void getValueAndDefaultTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getValueAndDefault("key","value"));
	}
	
	@Test
	public void getValueAndDefaultValueNullTest() {
		assertEquals("value",PropertiesReader.getValueAndDefault("keyNotFound","value"));
	}

	@Test
	public void setPropertyIntTest() {
		PropertiesReader.setProperty("intKey", 1);
		assertTrue(1==PropertiesReader.getInteger("intKey").intValue());
	}

	@Test
	public void setPropertyLongTest() {
		Long longKey = 2L;
		PropertiesReader.setProperty("longKey", longKey);
		assertEquals(longKey,PropertiesReader.getLong("longKey"));
	}

	@Test
	public void setPropertyFloatTest() {
		Float floatKey = 3.0f;
		PropertiesReader.setProperty("floatKey", floatKey);
		assertEquals(floatKey,PropertiesReader.getFloat("floatKey"));
	}

	@Test
	public void setPropertyDoubleTest() {
		Double doubleKey = 3.0d;
		PropertiesReader.setProperty("doubleKey", doubleKey);
		assertEquals(doubleKey,PropertiesReader.getDouble("doubleKey"));
	}

	@Test
	public void setPropertyBooleanTest() {
		boolean booleanKey = true;
		PropertiesReader.setProperty("booleanKey", booleanKey);
		assertEquals(true,PropertiesReader.getBoolean("booleanKey"));
	}

	@Test
	public void containsTest() {
		PropertiesReader.setProperty("key", "value");
		assertTrue(PropertiesReader.contains("value"));
		assertFalse(PropertiesReader.contains("valueNotFound"));
	}

	@Test
	public void containsKeyTest() {
		PropertiesReader.setProperty("key", "value");
		assertTrue(PropertiesReader.containsKey("key"));
		assertFalse(PropertiesReader.containsKey("keyNotFound"));
	}

	@Test
	public void containsValueTest() {
		PropertiesReader.setProperty("key", "value");
		assertTrue(PropertiesReader.containsValue("value"));
		assertFalse(PropertiesReader.containsValue("valueNotFound"));
	}

	@Test
	public void getIntegerTest() {
		PropertiesReader.setProperty("integerKey", 1);
		assertTrue(1==PropertiesReader.getInteger("integerKey").intValue());
	}
	
	@Test
	public void getIntegerKeyNotFoundTest() {
		assertNull(PropertiesReader.getInteger("integerKeyNotFound"));
	}

	@Test
	public void getIntTest() {
		PropertiesReader.setProperty("intKey", 1);
		assertTrue(1==PropertiesReader.getInt("intKey",0));
	}
	
	@Test
	public void getIntDefaultTest() {
		assertTrue(0==PropertiesReader.getInt("intKeyNotFound",0));
	}

	@Test
	public void getRequiredIntTest() {
		PropertiesReader.setProperty("intKey", 1);
		assertTrue(1==PropertiesReader.getRequiredInt("intKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredIntKeyNotFoundTest() {
		PropertiesReader.getRequiredInt("keyNotFound");
	}

	@Test
	public void getLongTest() {
		Long longKey = 2L;
		PropertiesReader.setProperty("longKey", longKey);
		assertTrue(2.0==PropertiesReader.getLong("longKey"));
	}
	
	@Test
	public void getLongKeyNotFoundTest() {
		assertNull(PropertiesReader.getLong("longKeyNotFound"));
	}

	@Test
	public void getLongDefaultTest() {
		Long longKey = 3L;
		PropertiesReader.setProperty("longKey", longKey);
		assertTrue(3.0==PropertiesReader.getLong("longKey",4));
		
		assertTrue(4==PropertiesReader.getLong("longKeyNotFound",4));
	}

	@Test
	public void getRequiredLongTest() {
		Long longKey = 3L;
		PropertiesReader.setProperty("longKey", longKey);
		assertTrue(3.0==PropertiesReader.getRequiredLong("longKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredLongKeyNotFoundTest() {
		PropertiesReader.getRequiredLong("keyNotFound");
	}

	@Test
	public void getBooleanTest() {
		boolean booleanKey= true;
		PropertiesReader.setProperty("booleanKey", booleanKey);
		assertTrue(PropertiesReader.getBoolean("booleanKey"));
	}
	
	@Test
	public void getBoolean0Test() {
		String booleanKey= "0";
		PropertiesReader.setProperty("booleanKey", booleanKey);
		assertFalse(PropertiesReader.getBoolean("booleanKey"));
	}
	
	@Test
	public void getBooleanKeyNotFoundTest() {
		assertNull(PropertiesReader.getBoolean("booleanKeyNotFound"));
	}

	@Test
	public void getBooleanDefaultTest() {
		PropertiesReader.setProperty("booleanKey", true);
		assertTrue(PropertiesReader.getBoolean("booleanKey",true));
		assertTrue(PropertiesReader.getBoolean("booleanKeyNotFound",true));
		assertFalse(PropertiesReader.getBoolean("booleanKeyNotFound2",false));
	}

	@Test
	public void getRequiredBooleanTest() {
		boolean booleanKey= true;
		PropertiesReader.setProperty("booleanKey", booleanKey);
		assertTrue(PropertiesReader.getRequiredBoolean("booleanKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredBooleanKeyNotFoundTest() {
		PropertiesReader.getRequiredBoolean("keyNotFound");
	}

	@Test
	public void getFloatTest() {
		Float floatKey = 3F;
		PropertiesReader.setProperty("floatKey", floatKey);
		assertTrue(3.0==PropertiesReader.getFloat("floatKey"));
	}
	
	@Test
	public void getFloatValueNullTest() {
		assertNull(PropertiesReader.getFloat("floatKeyNotFound"));
	}

	@Test
	public void getFloatDefaultTest() {
		Float floatKey = 3F;
		PropertiesReader.setProperty("floatKey", floatKey);
		assertTrue(3.0==PropertiesReader.getFloat("floatKey",2));
		assertTrue(4==PropertiesReader.getFloat("floatKeyNotFound",4));
	}

	@Test
	public void getRequiredFloatTest() {
		Float floatKey = 3F;
		PropertiesReader.setProperty("floatKey", floatKey);
		assertTrue(3.0==PropertiesReader.getFloat("floatKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredFloatKeyNotFoundTest() {
		PropertiesReader.getRequiredFloat("floatKeyNotFound");
	}

	@Test
	public void getDoubleTest() {
		Double doubleKey = 3d;
		PropertiesReader.setProperty("doubleKey", doubleKey);
		assertTrue(3.0==PropertiesReader.getDouble("doubleKey").doubleValue());
	}
	
	@Test
	public void getDoubleValueNotFoundTest() {
		assertNull(PropertiesReader.getDouble("doubleKeyNotFound"));
	}

	@Test
	public void getDoubleDefaultTest() {
		Double doubleKey = 3d;
		PropertiesReader.setProperty("doubleKey", doubleKey);
		assertTrue(3.0==PropertiesReader.getDouble("doubleKey",4));
		assertTrue(5==PropertiesReader.getDouble("doubleKeyNotFound",5));
	}

	@Test
	public void getRequiredDoubleTest() {
		PropertiesReader.setProperty("doubleKey", 3.0);
		assertTrue(3.0==PropertiesReader.getRequiredDouble("doubleKey"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void getRequiredDoubleKeyNotFoundTest() {
		PropertiesReader.setProperty("doubleKey", "3.0");
		assertNull(PropertiesReader.getRequiredDouble("doubleKeyNotFound"));
	}

	@Test
	public void setPropertyTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getProperty("key","value1"));
	}

	@Test
	public void getPropertyDefaultTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getProperty("key","value1"));
	}
	
	@Test
	public void getPropertyKeyNotFoundDefaultKeyTest() {
		assertEquals("expectedValue",PropertiesReader.getProperty("keyNotFound","expectedValue"));
	}

	@Test
	public void getPropertyTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getProperty("key"));
	}
	
	@Test
	public void getPropertyKeyNotFoundTest() {
		assertNull(PropertiesReader.getProperty("keyNotFound"));
	}

	@Test
	public void clearTest() {
		Properties properties = new Properties();
		assertNull(properties.get("key"));
		
		properties.put("key", "value");
		assertEquals("value",properties.get("key"));
		
		PropertiesReader.clear();
		assertNull(PropertiesReader.getProperty("key"));
	}

	@Test
	public void getRequiredPropertyTest() {
		PropertiesReader.setProperty("key", "value");
		assertEquals("value",PropertiesReader.getRequiredProperty("key"));
	}
	
	@Test(expected=IllegalStateException.class)
	public void getRequiredPropertyNotFoundTest() {
		PropertiesReader.setProperty("key", "value");
		assertNull(PropertiesReader.getRequiredProperty("keyNotFound"));
	}

	@Test
	public void getPropertyArrayTest() {
		PropertiesReader.setProperty("keyA", "a,b");
		assertEquals("a",PropertiesReader.getPropertyArray("keyA")[0]);
		assertEquals("b",PropertiesReader.getPropertyArray("keyA")[1]);
	}
	
	@Test
	public void getPropertyListTest() {
		PropertiesReader.setProperty("keyA", "a,b");
		assertEquals("a",PropertiesReader.getPropertyList("keyA").get(0));
		assertEquals("b",PropertiesReader.getPropertyList("keyA").get(1));
	}
}
