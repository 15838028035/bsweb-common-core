package com.lj.app.core.common.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.app.core.common.exception.BusinessException;
import com.lj.app.core.common.util.StringUtil;

import jxl.Cell;
import jxl.Sheet;

/**
 * 
 * 用户服务
 *
 */
@Service("upmUserService")
@Transactional
public class UpmUserServiceImpl extends BatchOptBaseServiceImpl implements UpmUserService {

  @Override
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
      String loginAcct = loginAcctCell.getContents().trim();

      try {

        if (StringUtil.isBlank(loginAcct)) {
          exceptionMsgList.add("第" + i + "行不能为空");
          continue;
        }

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
