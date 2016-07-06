package com.lj.app.core.common.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ExcelUtilTest extends AbstractBaseSpringTransactionTestCase{

	@Test
	public void exportExcelTest() {
		String fileName = "fileName.xls";
		String[] Title =new String[]{"title"};
		List<Object> listContent = new ArrayList<Object>();
		listContent.add("a");
		ExcelUtil.exportExcel(fileName, Title, listContent);
	}

}
