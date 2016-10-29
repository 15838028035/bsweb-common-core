package com.lj.app.core.common.flows.task.interceptor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowProcess;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.util.FileUtil;

public class LocalInterceptorTest extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/interceptor/flow1.xml"));
	}
	
	@Test
	public void taskTest()  throws Exception {
		FlowProcess flowProcess  = (FlowProcess) engine.flowProcessService().getInfoByKey(processId);
		
		assertEquals("interceptor",flowProcess.getFlowName());
		assertEquals("测试局部拦截器",flowProcess.getDisplayName());
		
		FlowOrder order = engine.startInstanceByName(processId, 2);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId());
		for(FlowTask task : tasks) {
			engine.executeTask(task.getId().toString(), "1");
		}
	}

}
