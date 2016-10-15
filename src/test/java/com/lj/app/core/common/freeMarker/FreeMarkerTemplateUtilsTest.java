package com.lj.app.core.common.freeMarker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.lj.app.core.common.base.entity.UpmUser;
import com.lj.app.core.common.util.FileUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTemplateUtilsTest {

	private FreeMarkerTemplateUtils freeMarkerTemplateUtils;
	
	@Before
	public void setUp() {
		freeMarkerTemplateUtils = new FreeMarkerTemplateUtils();
	}
	
	/**
	 * 获取模版目录测试
	 * @throws Exception
	 */
	@Test
	public void setDirectoryForTemplateLoadingTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
	    freeMarkerTemplateUtils.setDirectoryForTemplateLoading(file);
	}

	/**
	 * 模版解析测试
	 * @throws Exception
	 */
	@Test
	public void processTemplateIntoStringTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
		
		String templatePath =file.getAbsolutePath()+ File.separator +"mailTest1.ftl";
		String templateFileContent = "hello ${userName}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		freeMarkerTemplateUtils.getConfiguration().setDirectoryForTemplateLoading(file);
        Template template = freeMarkerTemplateUtils.getConfiguration().getTemplate("mailTest1.ftl");  
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName","user1");
        
		freeMarkerTemplateUtils.processTemplateIntoString(template, map);
	}
	
	/**
	 *获取公共变量测试
	 */
	@Test
	public void getShareVarsTest(){
		Map shareMap = FreeMarkerTemplateUtils.getShareVars();
		assertNotNull(shareMap);
		assertTrue(shareMap.keySet().contains("now"));
		assertTrue(shareMap.keySet().contains("StringUtil"));
		assertTrue(shareMap.keySet().contains("PropertiesUtil"));
	}
	
	/**
	 *获取工具类测试
	 */
	@Test
	public void getToolsMapTest() {
		Map toolMap = FreeMarkerTemplateUtils.getToolsMap();
		assertNotNull(toolMap);
		assertTrue(toolMap.keySet().contains("StringUtil"));
		assertTrue(toolMap.keySet().contains("PropertiesUtil"));
	}
	
	/**
	 * 读取模版内容测试
	 */
	@Test
	public void readTemplateFileContentTest() throws Exception{
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"test1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
	}
	
	/**
	 * 从classpath读取模版内容测试
	 */
	@Test(expected = IOException.class)
	public void readTemplateFileContentFromClassPathTest() throws Exception{
		String templatePath =FreeMarkerTemplateUtils.TEST_CLASSPATH_TEMPLATE_ROOT_DIR+ File.separator +"test1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
	}
	
	/**
	 * 读取模版模版文件没有找到
	 */
	@Test(expected = FileNotFoundException.class)
	public void readTemplateFileContentTemplateNotFoundTest() throws Exception{
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"templateNotFound.ftl";
		assertFalse(FileUtil.exist(templatePath));
		String templateFileContent = "";
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
	}
	
	/***
	 * 简单邮件模版测试
	 */
	@Test
	public void processTest1()  throws Exception {
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("Username", "zhangsan");
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		File file = FileUtils.getFile(FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		freeMarkerTemplateUtils.setDirectoryForTemplateLoading(file);
		
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest1.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		
		assertEquals("hello zhangsan",text);
	}
	
	/***
	 * 简单邮件模版 异常测试
	 */
	@Test(expected = Exception.class)
	public void processTest2()  throws Exception {
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest2.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("Username1", "zhangsan");
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest2.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		assertEquals("",text);
	}
	
	/***
	 * 简单邮件list模版测试
	 */
	@Test
	public void processTest3()  throws Exception {
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest3.ftl";
		String templateFileContent = "<#list userList as user>${user.userName}</#list>";

		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
		
		Map<String,List> map = new HashMap<String,List>();
		
		List<UpmUser> upmUserList = new ArrayList<UpmUser>();
		
		UpmUser UpmUser = new UpmUser();
		UpmUser.setUserName("user1");
		upmUserList.add(UpmUser);
		map.put("userList",upmUserList);
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest3.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		
		assertEquals("user1",text);
	}
	
	/***
	 * 简单邮件list模版 异常测试，测试模版用户名为空
	 */
	@Test(expected = Exception.class)
	public void processTest4()  throws Exception {
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest4.ftl";
		String templateFileContent = "<#list userList as user>${user.userName}</#list>";

		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
		
		Map<String,List> map = new HashMap<String,List>();
		
		List<UpmUser> upmUserList = new ArrayList<UpmUser>();
		
		UpmUser UpmUser = new UpmUser();
		upmUserList.add(UpmUser);
		map.put("userList",upmUserList);
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest4.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		
		assertEquals("",text);
	}
	
	/***
	 * 简单邮件条件语句模版测试
	 */
	@Test
	public void processTest5()  throws Exception {
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest5.ftl";
		String templateFileContent = "<#if userName=='zhangsan'>你是张三<#else>你不是张三</#if>";

		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		String readTemplateFileContent = FreeMarkerTemplateUtils.readTemplateFileContent(templatePath);
		assertEquals(templateFileContent,readTemplateFileContent);
		assertTrue(FileUtil.exist(templatePath));
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("userName","zhangsan");
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest5.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		
		assertEquals("你是张三",text);
	}
}
