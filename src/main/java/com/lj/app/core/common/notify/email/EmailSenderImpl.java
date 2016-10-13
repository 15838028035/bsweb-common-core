package com.lj.app.core.common.notify.email;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.notify.entity.UpmNotice;
import com.lj.app.core.common.notify.service.UpmNoticeService;
import com.lj.app.core.common.util.SpringContextHolder;
import com.lj.app.core.common.util.StringUtil;

/**
 * 调用方法： IEmailSender emailSender = EmailSenderFactory.createEmailSenderImpl();
 * boolean isSuccess = emailSender.sendEmail("目标邮箱地址","邮件标题", "邮件正文内容");
 * 
 */

public class EmailSenderImpl implements IEmailSender {
	private static Log logger = LogFactory.getLog(EmailSenderImpl.class);

	public EmailSenderImpl() {

	}
	
	@Override
	public boolean sendEmail(String sendTo, String subject, String content) {
		try {
			if (StringUtil.isBlank(sendTo)) {
				logger.warn("======mail address is null,not send mail======");
				return false;
			}
			
			UpmNotice upmNotice = new UpmNotice();
			upmNotice.setTypeId("EMAIL");
			upmNotice.setParamA(sendTo);
			upmNotice.setParamB(subject);
			upmNotice.setContent(content);
			upmNotice.setCreateDateTime(new Date());
			upmNotice.setSendBeginDate(new Date());
			upmNotice.setSendEndDate(new Date());
			
			UpmNoticeService<UpmNotice> upmNoticeService = SpringContextHolder.getBean("upmNoticeService");
			
			upmNoticeService.insertObject(upmNotice);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
