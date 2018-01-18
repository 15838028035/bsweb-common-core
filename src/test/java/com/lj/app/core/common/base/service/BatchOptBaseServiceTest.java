package com.lj.app.core.common.base.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class BatchOptBaseServiceTest extends AbstractBaseSpringTransactionTestCase  { 

	@Autowired
	private BatchOptBaseService batchOptBaseService;
	
	@Test
	public void batchProcessCountTest() {
		int batchProcessCount = batchOptBaseService.BATCHPROCESSCOUNT;
		assertTrue(batchProcessCount>=100);
	}

}
