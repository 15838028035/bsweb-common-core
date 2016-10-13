package com.lj.app.core.common.freeMarker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.util.ClassUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplateUtils {

	private static Log log = LogFactory.getLog(FreeMarkerTemplateUtils.class);
	
	public static final String TEST_TEMPLATE_ROOT_DIR = PropertiesUtil.getProperty("TEST_TEMPLATE_ROOT_DIR", "d:\\TEST_TEMPLATE_ROOT_DIR");
	public static final String TEST_CLASSPATH_TEMPLATE_ROOT_DIR = PropertiesUtil.getProperty("TEST_CLASSPATH_TEMPLATE_ROOT_DIR", "classpath:\\TEST_TEMPLATE_ROOT_DIR");
	
	private Configuration configuration = null;
	
	 public FreeMarkerTemplateUtils() {  
         configuration = new Configuration();  
         configuration.setDefaultEncoding("UTF-8");  
     }  
	 
	public Configuration getDirectoryForTemplateLoading(File file) throws Exception {
		configuration.setDirectoryForTemplateLoading(file);
		return configuration;
	}
	
	public static String processTemplateIntoString(Template template, Object model)
			throws IOException, TemplateException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * 获得公共的配置变量
	 * @return
	 */
	public static Map getShareVars() {
		Map templateModel = new HashMap();
		templateModel.putAll(System.getProperties());
		templateModel.put("env", System.getenv());
		templateModel.put("now", new Date());
		templateModel.putAll(getToolsMap());
		return templateModel;
	}

	/** 得到模板可以引用的工具类  */
	public static Map getToolsMap() {
	    Map toolsMap = new HashMap();
	    String[] tools = PropertiesUtil.getPropertyArray("template_tools_class");
	    for(String className : tools) {
	        try {
	            Object instance = ClassUtil.newInstance(className);
	            toolsMap.put(Class.forName(className).getSimpleName(), instance);
	            log.debug("put tools class:"+className+" with key:"+Class.forName(className).getSimpleName());
	        }catch(Exception e) {
	        	log.error("cannot load tools by className:"+className+" cause:"+e);
	        }
	    }
	    return toolsMap;
	}

	/**
	 * 读取模板文件内容
	 * 
	 * @return 模板文件内容
	 */
	public static String readTemplateFileContent(String templatePath) throws FileNotFoundException, IOException,Exception {
		String templateFileContent = null;
		File templateFile = new File(templatePath);
		templateFileContent = org.apache.commons.io.FileUtils.readFileToString(templateFile, "UTF-8");
		return templateFileContent;
	}

	/**
	 * 写入模板文件内容
	 * 
	 */
	public static String writeTemplateFileContent(String templatePath, String templateFileContent)throws FileNotFoundException, IOException,Exception {
	    File templateFile = new File(templatePath);
	    org.apache.commons.io.FileUtils.writeStringToFile(templateFile, templateFileContent, "UTF-8");
	    return templateFileContent;
	}
	
}
