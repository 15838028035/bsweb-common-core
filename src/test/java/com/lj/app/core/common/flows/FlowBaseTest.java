package com.lj.app.core.common.flows;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.lj.app.core.common.flows.cfg.Configuration;
import com.lj.app.core.common.flows.service.FlowEngine;
import com.lj.app.core.common.flows.service.FlowProcessService;
import com.lj.app.core.common.flows.service.FlowQueryService;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-base.xml")
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class FlowBaseTest extends AbstractBaseSpringTransactionTestCase {
	protected String processId;
	protected FlowEngine engine;
	@Autowired
	protected FlowProcessService flowProcessService;
	@Autowired
	protected FlowQueryService flowQueryService;
	
	protected  FlowEngine getEngine() {
		return new Configuration().buildSnakerEngine();
	}
}
