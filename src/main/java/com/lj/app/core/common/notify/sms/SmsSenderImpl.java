package com.lj.app.core.common.notify.sms;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.util.StringUtil;



/**
 * 调用方法： 
 * 		ISmsSender smsSender = SmsSenderFactory.createSmsSenderImpl();
 *  	boolean isSuccess = smsSender.sendSms("电话号码", "内容" ,"发送开始时间" ,"发送截止时间");
 * 
 */

public class SmsSenderImpl implements ISmsSender {// client
	private static Log logger = LogFactory.getLog(SmsSenderImpl.class);
	
	public SmsSenderImpl() {
	}
	
	public boolean sendSms(String mobile, String content,Timestamp beginTime, Timestamp endTime) {
		try {
			if(StringUtil.isBlank(mobile)){
				logger.warn("======phone number is null,not send sms======");
				return false;
			}
			//FIXME
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
