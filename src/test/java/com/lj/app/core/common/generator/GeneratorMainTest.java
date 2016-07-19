package com.lj.app.core.common.generator;

import org.junit.Test;

import com.lj.app.core.common.generator.util.FileHelper;

public class GeneratorMainTest {

	@Test
	public void generateTalbeTest() throws Exception{
		GeneratorProperties.setProperty("basepackage", "com.lj.app.bsweb.upm.user");
		GeneratorProperties.setProperty("basepackage_dir", GeneratorProperties.getProperty("basepackage").replace(".", "/"));
		
		GeneratorProperties.setProperty("outRoot", "e:\\generator-output");
		
		GeneratorFacade g = new GeneratorFacade();
		g.clean();
		g.getGenerator().addTemplateRootDir(FileHelper.getFile("classpath:template"));
		
		 
		g.generateByTable("Upm_Dictionary");

		System.out.println("");
		System.out
				.println("***************************************************************");
		System.out
				.println("*********************Generate Success**************************");
		System.out
				.println("***************************************************************");
	}

}
