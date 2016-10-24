package com.lj.app.core.common.flows.task.transfer;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.util.FileUtil;

public class TransferTaskTest extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/transfer/flow1.xml"));
	}
	
	@Test
	public void taskTest()  throws Exception {
		FlowOrder order = engine.startInstanceByName("transfer", 0);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId());
		for(FlowTask task : tasks) {
		/*	engine.flowTaskService().createNewTask(task.getId(), 0, "test");
			engine.flowTaskService().complete(task.getId());*/
		}
	}

}
