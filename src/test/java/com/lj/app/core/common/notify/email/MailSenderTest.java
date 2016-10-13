package com.lj.app.core.common.notify.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.base.entity.UpmUser;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class MailSenderTest extends AbstractBaseSpringTransactionTestCase{

	@Autowired
	private MailSender mailSender;
	
	@Test
	public void sendMailTest() throws Exception {
		String toAddress = "liujie09_24@163.com";
		String subject = "testSubject";
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("path","path");
		info.put("username","username");
		info.put("fromAddress","fromAddress");
		info.put("sendTime","sendTime");
		info.put("checkcode","checkcode");
		
		info.put("id", "20");
		
		String ftlName = "mailTest.ftl";
		boolean isAsync = false;
		mailSender.sendMail(toAddress, subject, info, ftlName, isAsync);
	}
	
	@Test
	public void simpleMailTest() throws Exception{
		
		String toAddress = "liujie09_24@163.com";
		String subject = "testSubject";
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("path","path");
		info.put("username","username");
		info.put("fromAddress","fromAddress");
		info.put("sendTime","sendTime");
		info.put("checkcode","checkcode");
		
		info.put("id", "20");
		
		String ftlName = "mailTest.ftl";
		boolean isAsync = true;
		mailSender.sendMail(toAddress, subject, info, ftlName, isAsync);
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
	public void getMailTextTest() {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","mailTest1.ftl");
		info.put("userName","user1");
		
		String text = mailSender.getMailText(info);
		
		assertTrue(text.contains("user1"));
	}
	
	@Test
	public void getMailTextTemplateListTest() {
		
		List<UpmUser> upmUserList = new ArrayList<UpmUser>();
		UpmUser upmUser = new UpmUser();
		upmUser.setId(1);
		upmUser.setUserName("userName1");
		upmUserList.add(upmUser);
		
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","mailTest2.ftl");
		info.put("userList", upmUserList);
		
		String text = mailSender.getMailText(info);
		
		assertTrue(text.contains("userName1"));
	}
	
	@Test
	public void getMailTextExceptionTest() {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("ftlName","mailExceptionTest.ftl");
		assertEquals("Mail template not found ,content must be null","",mailSender.getMailText(info));
	}
}
