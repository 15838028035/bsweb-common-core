package com.lj.app.core.common.base.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.exception.BusinessException;


@Service("batchOptBaseService")
public abstract class BatchOptBaseServiceImpl extends BaseServiceImpl implements BatchOptBaseService {

	/**
	 * 校验单元格行 在列范围j内，若出现不为空的cell，则认为该行为有效行;无效行跳过执行
	 * 
	 * @param sheet
	 * @param i
	 * @param column
	 * @param excelFieldSize
	 * @return
	 */
	public boolean validateRow(Sheet sheet, int i, int column,
			int excelFieldSize) {

		for (int j = 0; j < column && j < excelFieldSize; j++) {
			Cell cell = sheet.getCell(j, i);
			String value = cell.getContents() != null ? cell.getContents()
					.trim() : "";
			if (!value.equals(""))
				return true;
		}
		return false;
	}
	
	/**
	 * 校验上传文件
	 * @param f
	 * @return
	 * @throws JXLException
	 * @throws IOException
	 */
	public void  importAndCheck(File f,String initfilename,String templateFileContentType) throws Exception{
		checkAndImportExcelData(f, "批量导入Excel", 0l, 0l,
				0l, "0");
	}
	
	/**
	 * 检查并导入EXCEL数据，需要事务的支持
	 */
	public void checkAndImportExcelData(File template, String batchOperType,
			Long operMainAcctId, Long appId, Long adminAcctSeq,
			String loginOrgId) throws Exception {
		BusinessException businessException = null;
		try {

			String excelName = "";
			HashMap<String, String> loginAcctMap = new HashMap<String, String>();
			int startRow = 2;
			Workbook wb = Workbook.getWorkbook(template);
			Sheet sheet = wb.getSheet(0);
			int column = sheet.getColumns(); // 列数
			int row = sheet.getRows(); // 行数
			int total = row - startRow + 1; // 一共导入的条数
			if (startRow > row) {// 开始行数不能大于最大行数
				throw new BusinessException("开始行数不能大于最大行数"+row);
			}
			int endRow = 0;
			// 根据输入的起使行，确定创建的最后行
			endRow = (startRow + batchProcessCount - 1) <= row ? (startRow
					+ batchProcessCount - 1) : row;
			List excelFieldList = new ArrayList();
					
			checkBatchExcelData(appId, adminAcctSeq, loginOrgId,
						 null,
					 excelFieldList, startRow,
						sheet, column, row, endRow);
			
			this.insertBatch(excelFieldList);
			
			wb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void checkBatchExcelData(Long appId, Long adminAcctSeq,
			String loginOrgId, 
			BatchOptBaseService batchOptBaseService,
			List excelFieldList,
		 int startRow, Sheet sheet,
			int column, int row, int endRow) throws Exception {
		// 批量错误信息提示
		List<String> exceptionMsgList = new ArrayList<String>();
		List<String> warnMsgList = new ArrayList<String>();
		for (int i = (startRow - 1); i < endRow; i++) {
			if (!validateRow(sheet, i, column, excelFieldList.size())) {
				continue;
			}
			Cell loginAcctCell = sheet.getCell(0, i);

			try {
				//具体业务处理逻辑
			} catch (Exception e) {
				exceptionMsgList.add(e.getMessage() + "行数" +i);
			}

		}

		// 根据抛出异常判断是否通过，或者是警告
		if (exceptionMsgList.size() > 0 || warnMsgList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body><p>");
			for (String msg : exceptionMsgList)
				sb.append(msg + "</br>");
			sb.append("</p></body></html>");
			logger.info("exceptionMsgList>>>>>>" + sb.toString());
			throw  new BusinessException(sb.toString());
		}
	}
		
}
