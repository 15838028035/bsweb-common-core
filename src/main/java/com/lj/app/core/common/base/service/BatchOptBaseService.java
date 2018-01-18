package com.lj.app.core.common.base.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;

import jxl.JXLException;
import jxl.Sheet;

/**
 * 批量操作baseService
 *
 */
public interface BatchOptBaseService extends BaseService {

  public static final  Log LOGGER = LogFactory.getLog(BatchOptBaseService.class);
  public static final int BATCHPROCESSCOUNT = PropertiesUtil.getInt("batchProcessCount", 3000);

  /**
   * 校验单元格行 在列范围j内，若出现不为空的cell，则认为该行为有效行;无效行跳过执行
   * 
   * @param sheet sheet
   * @param i 第几行
   * @param column 第几列
   * @param excelFieldSize excle大小
   * @return 是否通过
   */
  public boolean validateRow(Sheet sheet, int i, int column, int excelFieldSize);

  /**
   * 校验上传文件
   * 
   * @param f 文件
   * @throws JXLException 异常
   * @throws IOException 异常
   */
  public void importAndCheck(File f, String initfilename, String templateFileContentType) throws Exception;

  /**
   * 检查并导入EXCEL数据，需要事务的支持
   */
  public void checkAndImportExcelData(File template, String batchOperType, Long operMainAcctId, Long appId,
      Long adminAcctSeq, String loginOrgId) throws Exception;

  public void checkBatchExcelData(Long appId, Long adminAcctSeq, String loginOrgId,
      BatchOptBaseService batchOptBaseService, List excelFieldList, int startRow, Sheet sheet, int column, int row,
      int endRow) throws Exception;

}
