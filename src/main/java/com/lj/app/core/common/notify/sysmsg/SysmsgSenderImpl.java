package com.lj.app.core.common.notify.sysmsg;

import com.lj.app.core.common.properties.PropertiesUtil;


/**
 * 调用方法： 
 * 		ISysmsgSender sysmsgSender = SysmsgSenderFactory.createSysmsgSenderImpl();
 *  	boolean isSuccess = sysmsgSender.sendSysmsg("标题", "内容" ,"主帐号");
 * 
 */
public class SysmsgSenderImpl implements ISysmsgSender {
	
	public boolean sendSysmsg(String title, String content,Long mainAcctId) {
		try {			
			String endpoint = PropertiesUtil.getProperty("UPMNoticeServiceAddr");
			System.out.println("UPMNoticeServiceAddr:" + endpoint);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
