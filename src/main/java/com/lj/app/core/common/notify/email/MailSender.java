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

import com.lj.app.core.common.freeMarker.FreeMarkerTemplateUtils;
import com.lj.app.core.common.notify.entity.UpmNotice;
import com.lj.app.core.common.notify.service.UpmNoticeService;
import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.util.FileUtil;
import com.lj.app.core.common.util.StringUtil;

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
	
	public static final String MAIL_TO_ADDRESS =  "toAddress";//邮件发送地址
	public static final String MAIL_SUBJECT =  "subject";//邮件主题
	public static final String MAIL_FTL_NAME =  "ftlName";//邮件模版名称
	
	 public MailSender()  {  
         configuration = new Configuration();  
         configuration.setDefaultEncoding("UTF-8"); 
         File file =null;
         try{
        	 file  = FileUtil.getFileByClassLoader(MAILT_EMPLATE_DIR);
         }catch(Exception e){
        	 e.printStackTrace();
         }
         
         if (file==null) {
        	String  mailt_emplate_dir_config =  PropertiesUtil.getProperty(MAILT_EMPLATE_DIR);
        	log.debug("mailt_emplate_dir_config="+mailt_emplate_dir_config);
        	 file= new File(mailt_emplate_dir_config);
         }
         
         if (file!=null) {
        	 try{
         	 configuration.setDirectoryForTemplateLoading(file);
        	 }catch(Exception e) {
        		 e.printStackTrace();
        		 log.error(e);
        	 }
          }
				
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
            String templateName = (String)info.get("ftlName");
			template =  FreeMarkerTemplateUtils.getTemplate(configuration, templateName);
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
		
		String []toAddress = StringUtil.stringToArray(String.valueOf(info.get("toAddress")), ",");
		
		helper.setTo(toAddress);
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
	 * 异步发送,无需等待
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
		if (isAsync) {
			sendMailByAsynchronousMode(info);
		} else {
			sendMailBySynchronizationMode(info);
		}

	}
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
