package com.lj.app.core.common.flows;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.app.core.common.flows.service.FlowEngine;
import com.lj.app.core.common.flows.service.FlowEngineFacets;
import com.lj.app.core.common.util.AbstractBaseSpringTransactionTestCase;

public class FlowBaseTest extends AbstractBaseSpringTransactionTestCase {
	protected String processId;
	@Autowired
	protected  FlowEngineFacets flowEngineFacets;
	
	@Autowired
	protected  FlowEngine flowEngine;
	
	@Before
	public   void setUpRunEnvNew() {
		flowEngine = flowEngineFacets.getEngine();
	}
	
}
