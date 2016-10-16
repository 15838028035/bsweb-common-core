package com.lj.app.core.common.generator;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lj.app.core.common.generator.provider.db.DbTableFactory;
import com.lj.app.core.common.generator.provider.db.model.Table;
import com.lj.app.core.common.generator.provider.java.model.JavaClass;
import com.lj.app.core.common.generator.util.BeanHelper;
import com.lj.app.core.common.generator.util.ClassHelper;
import com.lj.app.core.common.generator.util.GLogger;
import com.lj.app.core.common.generator.util.StringHelper;

public class GeneratorFacade {
	
	private Generator generator = new Generator();
	
	public GeneratorFacade() {
		if (StringHelper.isNotBlank(GeneratorProperties.getProperty("outRoot"))) {
			generator.setOutRootDir(GeneratorProperties.getProperty("outRoot"));
		}
	}
	
	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
		 
	public void printAllTableNames() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		System.out.println("\n----All TableNames BEGIN----");
		for (int i = 0; i < tables.size(); i++) {
			String sqlName = ((Table) tables.get(i)).getSqlName();
			System.out.println("g.generateTable(\"" + sqlName + "\");");
		}
		System.out.println("----All TableNames END----");
	}

	public void generateByAllTable() throws Exception {
		List tables = DbTableFactory.getInstance().getAllTables();
		for (int i = 0; i < tables.size(); i++)
			generateByTable(((Table) tables.get(i)).getSqlName());
	}

	public void generateByTable(String tableName) throws Exception {
		Generator g = createGeneratorForDbTable();

		Table table = DbTableFactory.getInstance().getTable(tableName);
		generateByTable(g, table);
	}

	private void generateByTable(Generator g, Table table) throws Exception {
		GeneratorModel m = GeneratorModel.newFromTable(table);
		String displayText = table.getSqlName() + " => " + table.getClassName();
		generateBy(g, m, displayText);
	}

	public void generateByTable(String tableName, String className)
			throws Exception {
		Generator g = createGeneratorForDbTable();
		Table table = DbTableFactory.getInstance().getTable(tableName);
		table.setClassName(className);
		generateByTable(g, table);
	}

	public void generateByClass(Class clazz) throws Exception {
		Generator g = createGeneratorForJavaClass();
		GeneratorModel m = GeneratorModel.newFromClass(clazz);
		String displayText = "JavaClass:" + clazz.getSimpleName();
		generateBy(g, m, displayText);
	}

	private void generateBy(Generator g, GeneratorModel m, String displayText)
			throws Exception {
		System.out
				.println("***************************************************************");
		System.out.println("* BEGIN generate " + displayText);
		System.out
				.println("***************************************************************");
		List<Exception> exceptions = g.generateBy(m.templateModel, m.filePathModel);
		if (exceptions.size() > 0) {
			System.err.println("[Generate Error Summary]");
			for (Exception e : exceptions) {
				System.err.println("[GENERATE ERROR]:" + e);
				e.printStackTrace();
			}
		}
	}

	public void clean() throws IOException {
		Generator g = createGeneratorForDbTable();
		g.clean();
	}

	public void deleteOutRootDir() throws IOException {
		generator.deleteOutRootDir();
	}
	
	public Generator createGeneratorForDbTable() {
		Generator g = getGenerator();
		g.setRemoveExtensions(GeneratorProperties.getProperty("generator_removeExtensions"));
		return g;
	}

	private Generator createGeneratorForJavaClass() {
		Generator g = new Generator();
		g.setTemplateRootDir(new File("template/javaclass").getAbsoluteFile());
		g.setOutRootDir(GeneratorProperties.getRequiredProperty("outRoot"));
		g.setRemoveExtensions(GeneratorProperties.getProperty("generator_removeExtensions"));
		return g;
	}

	public static class GeneratorModel {
		public Map filePathModel;
		public Map templateModel;

		public GeneratorModel(Map templateModel, Map filePathModel) {
			this.templateModel = templateModel;
			this.filePathModel = filePathModel;
		}

		public static GeneratorModel newFromTable(Table table) {
			Map templateModel = new HashMap();
			templateModel.putAll(GeneratorProperties.getProperties());
			templateModel.put("table", table);
			templateModel.putAll(getShareVars());

			Map filePathModel = new HashMap();
			filePathModel.putAll(GeneratorProperties.getProperties());
			filePathModel.putAll(BeanHelper.describe(table));
			filePathModel.putAll(getShareVars());
			return new GeneratorModel(templateModel, filePathModel);
		}

		public static GeneratorModel newFromClass(Class clazz) {
			Map templateModel = new HashMap();
			templateModel.putAll(GeneratorProperties.getProperties());
			templateModel.put("clazz", new JavaClass(clazz));
			templateModel.putAll(getShareVars());

			Map filePathModel = new HashMap();
			filePathModel.putAll(GeneratorProperties.getProperties());
			filePathModel.putAll(BeanHelper.describe(clazz));
			filePathModel.putAll(getShareVars());
			return new GeneratorModel(templateModel, filePathModel);
		}
	}
	
	public static Map getShareVars() {
		Map templateModel = new HashMap();
		templateModel.putAll(System.getProperties());
		templateModel.putAll(GeneratorProperties.getProperties());
		templateModel.put("env", System.getenv());
		templateModel.put("now", new Date());
		templateModel.put(GeneratorConstants.DATABASE_TYPE.code, GeneratorProperties.getDatabaseType(GeneratorConstants.DATABASE_TYPE.code));
		templateModel.putAll(GeneratorContext.getContext());
		templateModel.putAll(getToolsMap());
		return templateModel;
		}

		/** 得到模板可以引用的工具类  */
		private static Map getToolsMap() {
		    Map toolsMap = new HashMap();
		    String[] tools = GeneratorProperties.getStringArray(GeneratorConstants.GENERATOR_TOOLS_CLASS);
		    for(String className : tools) {
		        try {
		            Object instance = ClassHelper.newInstance(className);
		            toolsMap.put(Class.forName(className).getSimpleName(), instance);
		            GLogger.debug("put tools class:"+className+" with key:"+Class.forName(className).getSimpleName());
		        }catch(Exception e) {
		            GLogger.error("cannot load tools by className:"+className+" cause:"+e);
		        }
		    }
		    return toolsMap;
		}

}