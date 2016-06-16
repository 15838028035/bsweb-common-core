package com.lj.app.bsweb.upm.user.service;

import java.io.File;
import java.io.IOException;

import jxl.JXLException;

import com.lj.app.core.common.base.service.BaseService;

public interface UpmUserService<UpmUser> extends BaseService {
	/**
	 * 根据用户id、用户名称、组织机构编号，查找接收人信息
	 * @param sendUserId
	 * @param sendUserName
	 * @param findOrgCode
	 * @return
	 */
	public String getReceiverBySendUserId(int sendUserId, String sendUserName,String findOrgCode,int subStringLength);
	
	/**
	 *验证码检查
	 */
	public boolean identifyingCodeCheck(Integer passwordCheckCount,String rand ,Integer passwordErrorMaxTimes,String identifyingCodeInput);
	
	/**
	 * 校验上传文件
	 * @param f
	 * @return
	 * @throws JXLException
	 * @throws IOException
	 */
	public String verifyCreateExcel(File f,String initfilename,String templateFileContentType) throws JXLException, IOException;
}
