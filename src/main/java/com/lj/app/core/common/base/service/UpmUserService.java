package com.lj.app.core.common.base.service;


public interface UpmUserService<UpmUser> extends BatchOptBaseService {
	/**
	 *验证码检查
	 */
	public boolean identifyingCodeCheck(Integer passwordCheckCount,String rand ,Integer passwordErrorMaxTimes,String identifyingCodeInput);

}
