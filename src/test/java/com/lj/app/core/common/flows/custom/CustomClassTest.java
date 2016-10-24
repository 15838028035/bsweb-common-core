package com.lj.app.core.common.flows.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.util.FileUtil;

public class CustomClassTest extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/custom/flow2.xml"));
	}
	
	@Test
	public void taskTest()  throws Exception {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("msg", "custom test");
		FlowOrder order = engine.startInstanceById(processId, null, args);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId());
		for(FlowTask task : tasks) {
			engine.executeTask(task.getId().toString(), null, args);
		}
	}
}
