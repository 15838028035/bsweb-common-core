package com.lj.app.core.common.flows.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.util.FileUtil;

public class SimpleTaskTest  extends FlowBaseTest {
	@Before
	public void before() throws Exception {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/simple/flow1.xml"));
		engine.flowProcessService().updateType(processId, "预算管理流程");
	}
	
	@Test
	public void taskTest()  throws Exception {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("task1.operator", new String[]{"admin管理员"});
		FlowOrder order = engine.startInstanceByName("simple", null, "2", args);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId().toString());
		for(FlowTask task : tasks) {
			engine.executeTask(task.getId().toString(), "admin管理员", args);
		}
	}
}
