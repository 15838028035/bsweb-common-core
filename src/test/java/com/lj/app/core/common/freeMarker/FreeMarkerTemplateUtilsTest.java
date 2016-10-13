package com.lj.app.core.common.freeMarker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.lj.app.core.common.util.FileUtil;

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
	public void getDirectoryForTemplateLoadingTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
	    freeMarkerTemplateUtils.getDirectoryForTemplateLoading(file);
	}

	/**
	 * 模版解析测试
	 * @throws Exception
	 */
	@Test
	public void processTemplateIntoStringTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
		
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

}
