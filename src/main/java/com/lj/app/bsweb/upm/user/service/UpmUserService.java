package com.lj.app.bsweb.upm.user.service;

import com.lj.app.core.common.base.service.BatchOptBaseService;

public interface UpmUserService<UpmUser> extends BatchOptBaseService {
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

}
