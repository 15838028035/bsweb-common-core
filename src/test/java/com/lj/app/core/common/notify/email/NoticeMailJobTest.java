package com.lj.app.core.common.notify.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class NoticeMailJobTest extends AbstractBaseSpringTransactionTestCase{
	
	@Autowired
	private NoticeMailJob noticeMailJob;

	@Test
	public void scanningTest() {
		noticeMailJob.scanning();
	}

}
