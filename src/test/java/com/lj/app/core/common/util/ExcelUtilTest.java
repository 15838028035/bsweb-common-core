package com.lj.app.core.common.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Test;

import com.lj.app.core.common.properties.PropertiesUtil;

public class ExcelUtilTest extends AbstractBaseSpringTransactionTestCase{
	
	/**
	 * 写入Excel测试
	 */
	@Test
	public void writeExcelTest() throws Exception {
		String fileName = "excelTest1";
		String[] Title =new String[]{"title"};
		List<LinkedHashMap> listContent = new ArrayList<LinkedHashMap>();
		LinkedHashMap map = new LinkedHashMap<String,String>();
		map.put("tilte", "标题1");
		listContent.add(map);
		
		LinkedHashMap map2 = new LinkedHashMap<String,String>();
		map2.put("tilte", "标题2");
		listContent.add(map2);
		
		String filePath = PropertiesUtil.getProperty("TEST_CLASSPATH_TEMPLATE_ROOT_DIR") +File.separator+ "excelTest1.xls";
		FileOutputStream os = new FileOutputStream(filePath);
		ExcelUtil.writeExcel(os, fileName, Title, listContent);
		
		ExcelUtil.readExcelAndPrintContent(filePath);
	}
	
	/**
	 * 写入Excel自动列宽测试
	 */
	@Test
	public void writeExcelAutoWidthTest() throws Exception {
		String fileName = "excelTest2";
		String[] Title =new String[]{"title1","title2","title3"};
		List<LinkedHashMap> listContent = new ArrayList<LinkedHashMap>();
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("tilte1", "标题1");
		map.put("tilte2", "标题22222222222222222222222222222222222222222222888888888888888899999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999测试测试");
		map.put("tilte3", "标题3");
		listContent.add(map);
		
		String filePath = PropertiesUtil.getProperty("TEST_CLASSPATH_TEMPLATE_ROOT_DIR") +File.separator+ "excelTest2.xls";
		FileOutputStream os = new FileOutputStream(filePath);
		ExcelUtil.writeExcel(os, fileName, Title, listContent);
		
		ExcelUtil.readExcelAndPrintContent(filePath);
	}
	
	/**
	 * 写入Excel长数字测试
	 */
	@Test
	public void writeExcelNumberTest() throws Exception {
		String fileName = "excelTest3";
		String[] Title =new String[]{"title1","title2","title3"};
		List<LinkedHashMap> listContent = new ArrayList<LinkedHashMap>();
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("tilte1", "标题1");
		map.put("tilte2", "12345678910999");
		map.put("tilte3", "标题3");
		listContent.add(map);
		
		String filePath = PropertiesUtil.getProperty("TEST_CLASSPATH_TEMPLATE_ROOT_DIR") +File.separator+ "excelTest3.xls";
		FileOutputStream os = new FileOutputStream(filePath);
		ExcelUtil.writeExcel(os, fileName, Title, listContent);
		
		ExcelUtil.readExcelAndPrintContent(filePath);
	}
	
	
	@Test
	public void excelDemoTest() throws Exception{
		  String title="报表测试";
		  String[] navTitle= {"第一行","第二行","第三行","第四行","第五行","第六行","第七行","第八行"};  
		  String[][] content={
				  {"1","2","第naionfdapfn三行","第四niaodnfoanfdas行","第noandfoasnjdf五行","第六sdfadsafas行","第afdadfasdfs七a行","第adfasfdasf八行"},
				  {"2","2","第三行","第四行","第五行","第六行","第七行","sssssssssss第八sss行"},
				  {"3","2","第三行","第四行","第五行","第六行","第七行","第八行sssssssssssss"},
				  {"4","2","第三行","第四行","第sssssssssssssss五行","第ssssssssssssssssssss六行","第七行","第八行sssssssss"},
				  {"5","2","第三行","第ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd四行","第五行","第六行","第七行","第八行"},
				  {"6","2","第三行","第四行","第五行","第六行","第七行","第八行"},
				  {"7","2","第三行","第四ddddddddddddddddddddddddddddddd行","第五行","第六行","第七行","第八行"},
				  {"8","2","第三行","第四行","第五行","第六行","第七行","第八行"},
				  {"9","2","第三行","第ddddddddddddddddddddddddddddddd四行","第五行","第六行","第七行","第八行"},
				  {"10","2","第三行","第四行","第五行","第六行","第七行","第八行"},
				  {"11","2","第三行","第四行","第五行","第六dddddddddddddd行","第七行","第八行"},
				  {"12","2","第三行","第四行","第五行","第六行","第七行","第八行"},
				  {"13","2","第三行","第四行","第五行","dddddddddddddddddddddd第六行","第七行","第八行"},
				  {"14","2","第三行","第四行","第五行","第dddddddddddddddddddddd六行","第七行","第八行"},
		  }; 
		  
		  String filePath=PropertiesUtil.getProperty("TEST_CLASSPATH_TEMPLATE_ROOT_DIR");
		  String fileName="excelDemo.xls";
		  File dir=new  File(filePath);
		  if(!dir.isDirectory()){
			  dir.mkdirs();
		  }
		  
	       File file = new File(filePath+"\\"+fileName);
	       WritableWorkbook workbook = Workbook.createWorkbook(file);  
	       WritableSheet sheet = workbook.createSheet("报表统计", 0);  //单元格
	       /**
	        * title
	        */
	       Label lab = null;  
	       WritableFont   wf2   =   new   WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
	       WritableCellFormat wcfTitle = new WritableCellFormat(wf2);
	       wcfTitle.setBackground(jxl.format.Colour.IVORY);  //象牙白
	       wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
	       //       wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
	       wcfTitle.setAlignment(Alignment.CENTRE); //设置垂直对齐
	       
	       CellView navCellView = new CellView();  
	       navCellView.setAutosize(true); //设置自动大小
	       navCellView.setSize(18);
	       
	       lab = new Label(0,0,title,wcfTitle); //Label(col,row,str);   
	       sheet.mergeCells(0,0,navTitle.length-1,0);
	       sheet.setColumnView(0, navCellView); //设置col显示样式
	       sheet.setRowView(0, 1600, false); //设置行高
	       sheet.addCell(lab);  
	       
	       
	       /**
	        * nav
	        */
	       jxl.write.WritableFont wfcNav =new jxl.write.WritableFont(WritableFont.ARIAL,12, WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfN=new WritableCellFormat(wfcNav);
	        
	        Color color = Color.decode("#0099cc"); // 自定义的颜色
			workbook.setColourRGB(Colour.ORANGE, color.getRed(),color.getGreen(), color.getBlue());
			
	       wcfN.setBackground(Colour.ORANGE);
	       wcfN.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
	       wcfN.setAlignment(Alignment.CENTRE); //设置水平对齐
	       wcfN.setWrap(false); //设置自动换行
	       for(int i=0;i<navTitle.length;i++){
	    	  lab = new Label(i,1,navTitle[i],wcfN); //Label(col,row,str);   
	    	  sheet.addCell(lab);  
	    	  sheet.setColumnView(i, new String(navTitle[i]).length());  
	       }
	       
	       /**
	        * 内容
	        */
	       jxl.write.WritableFont wfcontent =new jxl.write.WritableFont(WritableFont.ARIAL,12, WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	       WritableCellFormat wcfcontent = new WritableCellFormat(wfcontent);
	       wcfcontent.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
	       wcfcontent.setAlignment(Alignment.CENTRE);
	       CellView cellView = new CellView();  
	       cellView.setAutosize(true); //设置自动大小
	       for(int i=0;i<content.length;i++){  
	           for(int j=0;j<content[i].length;j++){  
	        	  sheet.setColumnView(i, cellView);//根据内容自动设置列宽  
	        	  lab = new Label(j,i+2,content[i][j],wcfcontent); //Label(col,row,str);  
	               sheet.addCell(lab);  
//	               sheet.setColumnView(j, new String(content[i][j]).length());  
	           }  
	       }  
	       
	       workbook.write();  
	       workbook.close();  
		}
}
