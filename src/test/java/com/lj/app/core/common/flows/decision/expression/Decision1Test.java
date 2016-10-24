package com.lj.app.core.common.flows.decision.expression;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.util.FileUtil;

public class Decision1Test extends FlowBaseTest {
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/decision/expression/flow1.xml"));
	}
	
	@Test
	public void  taskTest()  throws Exception {
		Map<String, Object> args = new HashMap<String, Object>();
		//args.put("task1.operator", new String[]{"1","2"});
		args.put("task2.operator", new String[]{"1"});
		//args.put("task3.operator", new String[]{"1","2"});
		args.put("content", "toTask2");
		FlowOrder order = engine.startInstanceById(processId, "2", args);
		System.out.println(order);
	}

}

