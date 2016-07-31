package com.lj.app.core.common.base.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.JXLException;
import jxl.Sheet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 批量操作baseService
 *
 */
public interface BatchOptBaseService extends BaseService{

	public static Log logger = LogFactory.getLog(BatchOptBaseService.class);
	public static int batchProcessCount = PropertiesUtil.getInt("batchProcessCount", 3000);
	
	/**
	 * 校验单元格行 在列范围j内，若出现不为空的cell，则认为该行为有效行;无效行跳过执行
	 * 
	 * @param sheet
	 * @param i
	 * @param column
	 * @param excelFieldSize
	 * @return
	 */
	public boolean validateRow(Sheet sheet, int i, int column,int excelFieldSize);
	
	/**
	 * 校验上传文件
	 * @param f
	 * @return
	 * @throws JXLException
	 * @throws IOException
	 */
	public void importAndCheck(File f,String initfilename,String templateFileContentType) throws Exception;
	
	/**
	 * 检查并导入EXCEL数据，需要事务的支持
	 */
	public void checkAndImportExcelData(File template, String batchOperType,
			Long operMainAcctId, Long appId, Long adminAcctSeq,
			String loginOrgId) throws Exception;
	
	
	public void checkBatchExcelData(Long appId, Long adminAcctSeq,
			String loginOrgId,
			BatchOptBaseService batchOptBaseService,
			List excelFieldList,
		 int startRow, Sheet sheet,
			int column, int row, int endRow) throws Exception;
			
}
