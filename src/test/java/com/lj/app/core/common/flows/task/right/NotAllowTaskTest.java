package com.lj.app.core.common.flows.task.right;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.util.FileUtil;

public class NotAllowTaskTest extends FlowBaseTest {


	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/task/right/flow1.xml"));
	}
	
	@Test
	public void taskTest()  throws Exception {
		/*Map<String, Object> args = new HashMap<String, Object>();
		args.put("task1.operator", new String[]{"2"});
		int  flowVersion = 2;
		FlowOrder order = engine.startInstanceByName(processId,flowVersion, args);
		System.out.println("order=" + order);
		List<FlowTask> tasks = flowQueryService.getActiveTasks(order.getId());
		for(FlowTask task : tasks) {
			engine.executeTask(task.getId().toString(), FlowEngine.ADMIN, args);
		}*/
	}

}