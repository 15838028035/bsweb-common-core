package com.lj.app.core.common.notify.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lj.app.core.common.notify.entity.UpmNotice;
import com.lj.app.core.common.notify.service.UpmNoticeService;
import com.lj.app.core.common.util.DateUtil;

@Component("noticeMailJob")
public class NoticeMailJob {
	@Autowired
	private UpmNoticeService<UpmNotice> upmNoticeService;
	@Autowired
	private MailSender mailSender;
	private static long currentCount = 0l;
	private static final long eachNum = 10;

	 //@Scheduled(cron="0 */30 * * * ? ")   //每5秒执行一次  
	public void scanning() {
		List<UpmNotice> list = upmNoticeService.getUapNoticeMail(currentCount,eachNum);
		currentCount = list.size() > 0 ? list.get(0).getId() : currentCount;
		for (UpmNotice notice : list) {
			Map<String, Object> info = new HashMap<String,Object>();
			String sendTime = DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
			info.put("content", notice.getContent());
			info.put("sendTime", sendTime);
			info.put("id", String.valueOf(notice.getId()));
			info.put("templateDir", "templateDir");
			info.put("templateFileName", "templateFileName");
			try {
				mailSender.sendMail(notice.getParamA(), notice.getParamB()+ sendTime, info, "mailTest.ftl", true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
