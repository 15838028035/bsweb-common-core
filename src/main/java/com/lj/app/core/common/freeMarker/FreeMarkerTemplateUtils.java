package com.lj.app.core.common.freeMarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplateUtils {

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
	
}
