package com.lj.app.core.common.flows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.util.FileUtil;

public class GeneratorTest extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("test/process/process.snaker"));
	}
	
	@Test
	public void  generatorTest() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("task1.operator", new String[]{"1"});
		FlowOrder order = engine.startInstanceById(processId, "2", args);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId());
		for(FlowTask task : tasks) {
			engine.executeTask(task.getId().toString(), "1");
		}
	}
}
