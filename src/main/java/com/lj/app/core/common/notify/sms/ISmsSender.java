package com.lj.app.core.common.notify.sms;

import java.sql.Timestamp;

/**
 * 
 * ISmsSender
 *
 */
public interface ISmsSender {
	public boolean sendSms(String mobile, String content, Timestamp beginTime, Timestamp endTime);
}