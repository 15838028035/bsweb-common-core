package com.lj.app.core.common.notify.email;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.lj.app.core.common.freeMarker.FreeMarkerTemplateUtils;
import com.lj.app.core.common.notify.model.UpmNotice;
import com.lj.app.core.common.notify.service.UpmNoticeService;
import com.lj.app.core.common.properties.PropertiesUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 邮件发送接口
 * 
 */
@Component
public class MailSender {

	private static Log log = LogFactory.getLog(MailSender.class);
	@Autowired
	private JavaMailSender javaMailSender;
	
	private Configuration configuration = null;
	
	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	private UpmNoticeService<UpmNotice> upmNoticeService;
	
	public static final String MAILT_EMPLATE_DIR =  "mailTemplate";
	
	 public MailSender() {  
         configuration = new Configuration();  
         configuration.setDefaultEncoding("UTF-8");  
     }  
	 
	/**
	 * 添加模板内容
	 * 
	 * @param content
	 * @return
	 */
	public String getMailText(Map<String, Object> info) {
		String htmlText = "";

		Template template = null;
		try {
			info.putAll(FreeMarkerTemplateUtils.getShareVars());
			
			File file = ResourceUtils.getFile("classpath:" +(info.get(MAILT_EMPLATE_DIR)==null?MAILT_EMPLATE_DIR:info.get(MAILT_EMPLATE_DIR)));
			
			configuration.setDirectoryForTemplateLoading(file);
            try {  
            	template = configuration.getTemplate((String)info.get("ftlName"));  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template,info);
					
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return htmlText;
	}

	/**
	 * 同步发送
	 * 
	 */
	protected void sendMailBySynchronizationMode(Map<String, Object> info)
			throws MessagingException {
		info.putAll(FreeMarkerTemplateUtils.getShareVars());
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		helper.setSubject(String.valueOf(info.get("subject")));
		helper.setFrom(PropertiesUtil.getPropertyTrim("mail-server-address"));
		helper.setTo(String.valueOf(info.get("toAddress")));
		helper.setText(this.getMailText(info), true);// 设置为true表示发送的是HTML
		
		try {
			javaMailSender.send(msg);
			if (null != info.get("id")) {
				upmNoticeService.delete(Integer.parseInt((String.valueOf(info.get("id")))));
			}

		} catch (MailException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 异步发送
	 * 
	 */
	protected void sendMailByAsynchronousMode(final Map<String, Object> info) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendMailBySynchronizationMode(info);
				} catch (Exception e) {
					log.info(e);
				}
			}
		});
	}

	/**
	 * 发送邮件
	 * 
	 * @throws MessagingException
	 * 
	 */
	public void sendMail(String toAddress, String subject,
			Map<String, Object> info, String ftlName, boolean isAsync)
			throws MessagingException {
		info.putAll(FreeMarkerTemplateUtils.getShareVars());
		info.put("toAddress", toAddress);
		info.put("subject", subject);
		info.put("ftlName", ftlName);
		info.put(MAILT_EMPLATE_DIR, MAILT_EMPLATE_DIR);
		if (isAsync) {
			sendMailByAsynchronousMode(info);
		} else {
			sendMailBySynchronizationMode(info);
		}

	}
}
