package com.lj.app.core.common.notify.sms;

  
public class SmsSenderFactory {

	public static ISmsSender createSmsSenderImpl(){
		return new SmsSenderImpl();
	}
}
