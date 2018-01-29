package com.lj.app.core.common.notify.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lj.app.core.common.notify.entity.UpmNotice;
import com.lj.app.core.common.notify.service.UpmNoticeService;
import com.lj.app.core.common.task.service.BaseTaskService;
import com.lj.app.core.common.util.DateUtil;

/**
 * 
 * 通知邮件job
 *
 */
@Component("noticeMailJob")
public class NoticeMailJob implements BaseTaskService {
  private static Log logger = LogFactory.getLog(NoticeMailJob.class);
  
  @Autowired
  private UpmNoticeService<UpmNotice> upmNoticeService;
  @Autowired
  private MailSender mailSender;
  private static Long currentCount = 0L;
  private static final Long EACH_NUM = 10L;

  /**
   * 执行定时任务
   */
  @Scheduled(cron = "0 */30 * * * ? ") // 每30分钟执行一次
  public void doRunTask() {
    List<UpmNotice> list = upmNoticeService.getUapNoticeMail(currentCount, EACH_NUM);
    currentCount = list.size() > 0 ? list.get(0).getId() : currentCount;
    for (UpmNotice notice : list) {
      Map<String, Object> info = new HashMap<String, Object>();
      String sendTime = DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss");
      info.put("content", notice.getContent());
      info.put("sendTime", sendTime);
      info.put("id", String.valueOf(notice.getId()));
      info.put("templateDir", "templateDir");
      info.put("templateFileName", "templateFileName");
      info.put("notice", notice);
      try {
        mailSender.sendMail(notice.getParamA(), notice.getParamB() + sendTime, info, "mailTest.ftl", true);
      } catch (MessagingException e) {
        logger.error(e);
      }
    }
  }

}
