package com.lj.app.core.common.notify.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.entity.UpmUser;
import com.lj.app.core.common.freeMarker.FreeMarkerTemplateUtils;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;
import com.lj.app.core.common.util.MultiThreadTestUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MailSenderTest extends AbstractBaseSpringTransactionTestCase{

	@Autowired
	private MailSender mailSender;
	
	private AtomicInteger executedCount = new AtomicInteger();
	
	@Before
	public void setUp() throws Exception {
		Configuration conf = new Configuration();
		File file = FileUtils.getFile(FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		conf.setDirectoryForTemplateLoading(file);
		mailSender.setConfiguration(conf);
	}
	
	/**
	 * 同步发送
	 * @throws Exception
	 */
	@Test
	public void sendMailTest() throws Exception {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
		
		String toAddress ="\"15838028035\"<liujie09_24@163.com>";
				
		String subject = "test subject";
		String ftlName = "processTest1.ftl";
		
		mailSender.sendMail(toAddress, subject, info, ftlName, false);
		
	}
	
	/**
	 * 模拟异步发送
	 * @throws Exception
	 */
	@Test
	public void simpleMailTest() throws Exception{
		final Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
				
		info.put("toAddress", "\"15838028035\"<liujie09_24@163.com>");
		info.put("subject", "test subject");
		info.put("ftlName", "processTest1.ftl");
		
		CountDownLatch doneSignel = MultiThreadTestUtil.execute(1, new Runnable() {
			public void run() {
				try {
					mailSender.sendMailBySynchronizationMode(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
				executedCount.getAndIncrement();
			}
		});
		
		doneSignel.await();
		assertTrue(executedCount.intValue()<10);
	}
	
	@Test
	public void sendMailTemplateNotFoundTest() throws Exception {
		String toAddress = "liujie09_24@163.com";
		String subject = "testSubject";
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("path","path");
		info.put("username","username");
		info.put("fromAddress","fromAddress");
		info.put("sendTime","sendTime");
		info.put("checkcode","checkcode");
		info.put("ftlName","mailTestNotFound.ftl");
		
		info.put("id", "20");
		
		String ftlName = "mailTestNotFound.ftl";
		boolean isAsync = false;
		
		mailSender.sendMail(toAddress, subject, info, ftlName, isAsync);
	}
	
	@Test
	public void getMailTextTest() throws Exception {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","mailTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
	}
	
	@Test
	public void getMailTextTemplateListTest()  throws Exception{
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest3.ftl";
		String templateFileContent = "<#list userList as user>${user.userName}</#list>";

		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		Map<String,Object> map = new HashMap<String,Object>();
		List<UpmUser> upmUserList = new ArrayList<UpmUser>();
		
		UpmUser UpmUser = new UpmUser();
		UpmUser.setUserName("user1");
		upmUserList.add(UpmUser);
		map.put("userList",upmUserList);
		
		Configuration conf = FreeMarkerTemplateUtils.getTestConfiguration();
		Template template = FreeMarkerTemplateUtils.getTemplate(conf, "processTest3.ftl");
		
		String text = mailSender.getMailText(map);
		System.out.println("Text=" + text);
		
	}
	
	@Test
	public void getMailTextExceptionTest() {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","mailExceptionTest.ftl");
		assertEquals("Mail template not found ,content must be null","",mailSender.getMailText(info));
	}
	
	/**
	 * 发送邮件性能测试
	 */
	@Test
	public void getMailTextSynPerTest() throws Exception {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
		
		String toAddress ="975545486@qq.com";
				
		String subject = "test subject";
		String ftlName = "processTest1.ftl";
		
		long startTime = System.currentTimeMillis();
		for(int i=0;i<=10000;i++){
			mailSender.sendMail(toAddress, subject, info, ftlName, true);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("runTime:" +((endTime-startTime)/1000) + "S");
	}
	
	/**
	 * 发送邮件性能测试
	 */
	@Test
	public void getMailTextAsyPerTest() throws Exception {
		final Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
				
		info.put("toAddress", "\"15838028035\"<975545486@qq.com>");
		info.put("subject", "test subject");
		info.put("ftlName", "processTest1.ftl");
		
		CountDownLatch doneSignel = MultiThreadTestUtil.execute(10, new Runnable() {
			public void run() {
				try {
					mailSender.sendMailBySynchronizationMode(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
				executedCount.getAndIncrement();
			}
		});
		
		doneSignel.await();
		assertTrue(executedCount.intValue()<10);
	}
	
	/**
	 * 发送邮件给多个用户正常测试
	 */
	@Test
	public void sendMailToManyUserTest() throws Exception {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
		
		String toAddress ="\"用户01\"<liujie09_24@163.com>,\"用户02\"<liujie09_24@163.com>";
				
		String subject = "test subject";
		String ftlName = "processTest1.ftl";
		mailSender.sendMail(toAddress, subject, info, ftlName, false);
	}
	
	/**
	 * 发送邮件给多个用户测试，其中一个或多个用户不存在
	 */
	@Test(expected = Exception.class)
	public void sendMailToManyUserUserNotFoundTest() throws Exception {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","processTest1.ftl");
		info.put("Username","user1");
		info.put(MailSender.MAILT_EMPLATE_DIR,FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR);
		String templatePath =FreeMarkerTemplateUtils.TEST_TEMPLATE_ROOT_DIR+ File.separator +"processTest1.ftl";
		String templateFileContent = "hello ${Username}";
		FreeMarkerTemplateUtils.writeTemplateFileContent(templatePath, templateFileContent);
		
		String text = mailSender.getMailText(info);
		assertTrue(text.contains("user1"));
		
		String toAddress ="liujie09_24@163.com,userNotFound@163.com";
				
		String subject = "test subject";
		String ftlName = "processTest1.ftl";
		mailSender.sendMail(toAddress, subject, info, ftlName, false);
	}
}
