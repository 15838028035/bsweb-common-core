package com.lj.app.core.common.base.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.exception.BusinessException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * 批量操作服务类
 *
 */
@Service("batchOptBaseService")
public abstract class BatchOptBaseServiceImpl extends BaseServiceImpl implements BatchOptBaseService {

  /**
   * 校验单元格行 在列范围j内，若出现不为空的cell，则认为该行为有效行;无效行跳过执行
   * 
   * @param sheet sheet
   * @param i 第几行
   * @param column 第几列
   * @param excelFieldSize excle大小
   * @return 是否通过
   */
  public boolean validateRow(Sheet sheet, int i, int column, int excelFieldSize) {

    for (int j = 0; j < column && j < excelFieldSize; j++) {
      Cell cell = sheet.getCell(j, i);
      String value = cell.getContents() != null ? cell.getContents().trim() : "";
      if (!value.equals(""))  {
        return true;
      }
    }
    return false;
  }

  /**
   * 校验上传文件
   * 
   * @param f 文件
   * @throws JXLException 异常
   * @throws IOException 异常
   */
  public void importAndCheck(File f, String initfilename, String templateFileContentType) throws Exception {
    checkAndImportExcelData(f, "批量导入Excel", 0L, 0L, 0L, "0");
  }

  /**
   * 检查并导入EXCEL数据，需要事务的支持
   */
  public void checkAndImportExcelData(File template, String batchOperType, Long operMainAcctId, Long appId,
      Long adminAcctSeq, String loginOrgId) throws Exception {
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
      if (startRow > row) { // 开始行数不能大于最大行数
        throw new BusinessException("开始行数不能大于最大行数" + row);
      }
      int endRow = 0;
      // 根据输入的起使行，确定创建的最后行
      endRow = (startRow + BATCHPROCESSCOUNT - 1) <= row ? (startRow + BATCHPROCESSCOUNT - 1) : row;
      List excelFieldList = new ArrayList();

      checkBatchExcelData(appId, adminAcctSeq, loginOrgId, null, excelFieldList, startRow, sheet, column, row, endRow);

      this.insertBatch(excelFieldList);

      wb.close();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }
  
  /**
   * 导入校验
   */
  public void checkBatchExcelData(Long appId, Long adminAcctSeq, String loginOrgId,
      BatchOptBaseService batchOptBaseService, List excelFieldList, int startRow, Sheet sheet, int column, int row,
      int endRow) throws Exception {
    // 批量错误信息提示
    List<String> exceptionMsgList = new ArrayList<String>();
    List<String> warnMsgList = new ArrayList<String>();
    for (int i = (startRow - 1); i < endRow; i++) {
      if (!validateRow(sheet, i, column, excelFieldList.size())) {
        continue;
      }
      Cell loginAcctCell = sheet.getCell(0, i);

      try {
        // 具体业务处理逻辑
      } catch (Exception e) {
        exceptionMsgList.add(e.getMessage() + "行数" + i);
      }

    }

    // 根据抛出异常判断是否通过，或者是警告
    if (exceptionMsgList.size() > 0 || warnMsgList.size() > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append("<html><body><p>");
      for (String msg : exceptionMsgList) {
        sb.append(msg + "</br>");
      }
      sb.append("</p></body></html>");
      LOGGER.info("exceptionMsgList>>>>>>" + sb.toString());
      throw new BusinessException(sb.toString());
    }
  }

}
