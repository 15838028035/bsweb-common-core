package com.lj.app.core.common.flows.task.config;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.util.FileUtil;

public class TaskConfigTest extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/config/flow1.xml"));
	}
	
	@Test
	public void taskTest()  throws Exception {
		FlowOrder order = engine.startInstanceByName("config", 0, "2", null);
		System.out.println("order=" + order);
	}
}
