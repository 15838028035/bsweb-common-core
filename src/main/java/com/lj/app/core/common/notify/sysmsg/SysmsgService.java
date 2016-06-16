package com.lj.app.core.common.notify.sysmsg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;

public class SysmsgService {

	private static Log logger = LogFactory.getLog(SysmsgService.class);

	public static boolean sendSmsInfo(String title, String content,
			Long mainAcctId) {
		boolean result = false;
		if (mainAcctId != null) {
			String systemName = PropertiesUtil.getProperty("systemName");
			content = systemName + ":" + content;
			ISysmsgSender sysmsgSender = SysmsgSenderFactory.createSysmsgSenderImpl();
					
			logger.warn("**********start invoke sms service:");
			result = sysmsgSender.sendSysmsg(title, content, mainAcctId);
			logger.warn("**********end invoke sms service:");
		}

		return result;
	}

}