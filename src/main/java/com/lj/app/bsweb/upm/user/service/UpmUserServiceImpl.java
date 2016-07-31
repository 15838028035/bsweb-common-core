package com.lj.app.bsweb.upm.user.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BatchOptBaseService;
import com.lj.app.core.common.base.service.BatchOptBaseServiceImpl;
import com.lj.app.core.common.exception.BusinessException;

@Service("upmUserService")
public class UpmUserServiceImpl extends BatchOptBaseServiceImpl implements UpmUserService{
	
	private static final WorkbookSettings WorkbookSetting = new WorkbookSettings();
	
	static {
		WorkbookSetting.setIgnoreBlanks(true);
	}
	
	/**
	 * 根据用户id、用户名称、组织机构编号，查找接收人信息
	 * @param sendUserId
	 * @param sendUserName
	 * @param findOrgCode
	 * @return
	 */
	public String getReceiverBySendUserId(int sendUserId, String sendUserName,String findOrgCode,int subStringLength){
		//TODO:根据当前人账号、姓名、组织机构查找要发送的代办人信息
		int subLength = 0;
	/*	if(CrmConstants.MARKET_MANAGER.equals(findOrgCode)){
			subLength=4;
		}
		if(CrmConstants.COMPANY_MANAGER.equals(findOrgCode)){
			subLength=2;
		}
		if(CrmConstants.INTERNAL_OFFICE.equals(findOrgCode)){
			subLength=4;
		}*/
		
		//获取用户的组织机构
		String bussinessCode=null;
		String findBussinessCode=null;
		String recUserName="";

		List<String>  bussinessCodeList = queryForList("findUserBussinessCode",sendUserId);
		if(bussinessCodeList!=null&& bussinessCodeList.size()>0) {
			bussinessCode = bussinessCodeList.get(0);
		}
		
		if(bussinessCode!=null) {
			findBussinessCode = bussinessCode.substring(0,subStringLength);
		}
		
		//FIXME:内勤节点人员查找处理
		List<String>  findRecUserNameList =new ArrayList<String>();
		if("INTERNAL_OFFICE".equals(findOrgCode)){
			 findRecUserNameList = queryForList("internalOfficeUserName",findBussinessCode);
		}else if("SALE_ASSISTANT".equals(findOrgCode)){
			 findRecUserNameList = queryForList("saleAssistantUserName",findBussinessCode);
		}
		else if("S003".equals(findOrgCode)){
			 findRecUserNameList = queryForList("serviceManager",findBussinessCode);
		}else if("S004".equals(findOrgCode)){
			// findRecUserNameList = queryForList("saleManager",findBussinessCode);
		}
		else if("SALE_ASSISTANT".equals(findOrgCode)){
			 findRecUserNameList = queryForList("saleAssistantUserName",findBussinessCode);
		}
		else if("MARKET_MANAGER".equals(findOrgCode)){
			 findRecUserNameList = queryForList("marketManager",findBussinessCode);
		}
		
		else {
		  findRecUserNameList = queryForList("findRecUserName",findBussinessCode);
		}
		
		if(findRecUserNameList!=null) {
			for(String strUser:findRecUserNameList) {
				recUserName = recUserName+ strUser+",";
			}
		}
		
		return recUserName;
	}
	
	/**
	 *验证码检查
	 */
	public boolean identifyingCodeCheck(Integer passwordCheckCount,String rand ,Integer passwordErrorMaxTimes,String identifyingCodeInput){
		if(passwordErrorMaxTimes!=null){
			if (passwordErrorMaxTimes < 0) {
				return true;
			}
		}else{
			passwordErrorMaxTimes = 0;
		}
		if (passwordCheckCount != null && passwordCheckCount >= passwordErrorMaxTimes) { // 输入错误密码次数大于3
			if (identifyingCodeInput.equals(rand)) {
				return true;
			} 
		}  
		if (passwordCheckCount == null || passwordCheckCount < passwordErrorMaxTimes) { // 输入错误密码次数小于3 不验证
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
			int startRow = 11;
			Workbook wb = Workbook.getWorkbook(template);
			Sheet sheet = wb.getSheet(0);
			int column = sheet.getColumns(); // 列数
			int row = sheet.getRows(); // 行数
			int total = row - startRow + 1; // 一共导入的条数
			if (startRow > row) {// 开始行数不能大于最大行数
				throw new BusinessException("tooLong");
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
			String loginAcct = loginAcctCell.getContents().trim();

			try {
				
				if(StringUtils.isBlank(loginAcct)){
					exceptionMsgList.add( "第" + i + "行不能为空");
					continue;
				}
				
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
