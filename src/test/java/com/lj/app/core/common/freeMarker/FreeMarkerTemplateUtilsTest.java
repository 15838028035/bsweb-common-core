package com.lj.app.core.common.freeMarker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import freemarker.template.Template;

public class FreeMarkerTemplateUtilsTest {

	private FreeMarkerTemplateUtils freeMarkerTemplateUtils;
	
	@Before
	public void setUp() {
		freeMarkerTemplateUtils = new FreeMarkerTemplateUtils();
	}
	
	@Test
	public void getDirectoryForTemplateLoadingTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
	    freeMarkerTemplateUtils.getDirectoryForTemplateLoading(file);
	}

	@Test
	public void processTemplateIntoStringTest() throws Exception {
		File file = ResourceUtils.getFile("classpath:mailTemplate");
		
		freeMarkerTemplateUtils.getConfiguration().setDirectoryForTemplateLoading(file);
        Template template = freeMarkerTemplateUtils.getConfiguration().getTemplate("mailTest1.ftl");  
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName","user1");
        
		freeMarkerTemplateUtils.processTemplateIntoString(template, map);
	}

}
