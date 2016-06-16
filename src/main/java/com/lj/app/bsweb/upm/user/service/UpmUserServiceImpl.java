package com.lj.app.bsweb.upm.user.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.JXLException;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.LoginInfoUtil;
import com.lj.app.core.common.base.service.BaseServiceImpl;

@Service("upmUserService")
public class UpmUserServiceImpl<UpmUser> extends BaseServiceImpl<UpmUser> implements UpmUserService<UpmUser>{
	
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
	public String verifyCreateExcel(File f,String initfilename,String templateFileContentType) throws JXLException, IOException{
		WritableWorkbook workbook = null;		
		Map<String,String> phoneMap = new HashMap<String,String>();
		try{
			Workbook in= Workbook.getWorkbook(f,WorkbookSetting); 
			//workbook = Workbook.createWorkbook(new File(batch.getPath()), in);
			WritableSheet sheet = workbook.getSheet("sheet1");
			int rowsCount = sheet.getRows();
			int colsCount = sheet.getColumns();
			
			
			Set<String> set = new HashSet<String>();
			List<String> usedLoginName = new ArrayList<String>();
			for(int i=2;i<rowsCount;i++){
//				init set
				Cell[] cells = sheet.getRow(i);
				String loginName = cells[3].getContents();
				if(loginName!=null && !"".equals(loginName.trim())){
					set.add(loginName);
				}
			}
			Long actid = LoginInfoUtil.getLoginMainAcctId();
		
			for(int i=2;i<rowsCount;i++){
				int lt = (sheet.getRow(1)).length;
				Cell[] cells = new Cell[lt];
				for (int j = 0; j < lt; j++) {
					cells[j] = sheet.getCell(j,i);
				}
				
			}
		}finally{
			if(workbook!=null){
				workbook.write();
				workbook.close();
			}
		}
		return null;
	}
}
