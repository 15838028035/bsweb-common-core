package com.lj.app.core.common.flows;

import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.flows.cfg.Configuration;
import com.lj.app.core.common.flows.service.FlowEngine;
import com.lj.app.core.common.flows.service.FlowProcessService;
import com.lj.app.core.common.flows.service.FlowQueryService;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

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
