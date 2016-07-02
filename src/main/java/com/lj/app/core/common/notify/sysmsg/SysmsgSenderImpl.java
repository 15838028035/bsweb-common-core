package com.lj.app.core.common.notify.sysmsg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.util.StringUtil;


/**
 * 调用方法： 
 * 		ISysmsgSender sysmsgSender = SysmsgSenderFactory.createSysmsgSenderImpl();
 *  	boolean isSuccess = sysmsgSender.sendSysmsg("标题", "内容" ,"主帐号");
 * 
 */
public class SysmsgSenderImpl implements ISysmsgSender {
	
	private static Log logger = LogFactory.getLog(SysmsgSenderImpl.class);
	
	public boolean sendSysmsg(String title, String content,Long mainAcctId) {
		try {			
			String endpoint = PropertiesUtil.getProperty("UPMNoticeServiceAddr");
			System.out.println("UPMNoticeServiceAddr:" + endpoint);
			
			if(StringUtil.trimBlank(content).length()>5000) {
				logger.warn("======content is to lang ,not send sysmsg======");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
