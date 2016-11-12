package com.lj.app.core.common.flows.reject;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;
import com.lj.app.core.common.util.FileUtil;

public class RejectTaskTest extends FlowBaseTest{
	
	@Before
	public void before() {
		engine = getEngine();
		processId = engine.flowProcessService().deploy(FileUtil.getStreamFromClasspath("com/lj/app/core/common/flows/reject/flow1.xml"));
	}
	
	@Test
	public void taskTest() throws Exception {
		engine.startInstanceById(processId);
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("number", 2);
		engine.executeTask("10", null, args);
		engine.executeAndJumpTask("11", null, args, "task1");
	}
}
