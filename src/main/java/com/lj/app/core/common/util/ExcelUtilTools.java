package com.lj.app.core.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilTools {
    public static final String NO_DATAS_ROW_ROWNUM_FLAG = null;

	/**
	 * @Title: readExcel Functional overview
	 * @param sheet sheet
	 * @param startRowNum startRowNum
	 * @param lastRowNum lastRowNum
	 * @return String[][] String[][]
	 * @throws IOException IOException
	 * @author: author
	*/
    public static  String[][] readExcel(Sheet sheet, int startRowNum, int lastRowNum) throws IOException {
        String[][] yuce = null;
        try {
            int rowsnum = sheet.getLastRowNum();
            int colsnum = 0;
            if(rowsnum < 1) {
                return null;
            } else {
                Row row = sheet.getRow(0);
                colsnum = row.getLastCellNum();
                Cell cell;
                rowsnum = (lastRowNum - startRowNum) > rowsnum ? (rowsnum + 1) : (lastRowNum - startRowNum + 1);
                yuce = new String[rowsnum][colsnum];
                for(int i = 0; i < rowsnum; i++) {
                    row = sheet.getRow(i + startRowNum);
                    boolean noDataRowFlag = true;
                    for(int j = 0; j < colsnum; j++) {
                        cell = row.getCell(j);
                        yuce[i][j] = getCellValue(cell);
                        if(null != yuce[i][j] && !"".equals(yuce[i][j].toString())) {
                            noDataRowFlag = false;
                        }
                    }
                    if(noDataRowFlag == true) {
                        yuce[i][0] = NO_DATAS_ROW_ROWNUM_FLAG;
                    }
                }
            }
        } catch(Exception e) {
            // Logger.error(e);
            System.out.println(e.getMessage());
        }
        return yuce;
    }
    
	/**
	 * @Title: getCellValue Functional overview
	 * @param cell cell
	 * @return String String
	 * 创建日期: 2014-05-26
	*/
    public static String getCellValue(Cell cell) {
        String str = "";
        if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if(HSSFDateUtil.isCellDateFormatted(cell)){
                double d = cell.getNumericCellValue();    
                Date date = HSSFDateUtil.getJavaDate(d); 
                str = DateUtil.formatDate(date, "yyyy-MM-dd");
            }else {
            	DecimalFormat df = new DecimalFormat("#");
            	str = df.format(cell.getNumericCellValue())	;
                //str = String.valueOf(cell.getNumericCellValue()).trim();
            }
        }else if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            str = cell.getStringCellValue().trim();
        }else if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            str = String.valueOf(cell.getBooleanCellValue()).trim();
        }else if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            str = "".trim();
        }
        return str.trim();
    }
    
    /**
     * 获取sheet
     * @param excelFile
     * @param excelFileFileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public  static  Sheet getSheet(File excelFile,String excelFileFileName) throws FileNotFoundException, IOException {

		InputStream is = new FileInputStream(excelFile);

		/** POI 方式读取EXCEL */
		Workbook hssfworkbook = null;
		if (excelFileFileName.lastIndexOf("xlsx") > 0) {
			hssfworkbook = new XSSFWorkbook(is);
		} else {
			hssfworkbook = new HSSFWorkbook(is);
		}
		Sheet rs = hssfworkbook.getSheetAt(0);// 第一个工作表
		return rs;
	}
}