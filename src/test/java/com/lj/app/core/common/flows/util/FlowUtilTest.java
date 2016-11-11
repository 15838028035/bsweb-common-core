package com.lj.app.core.common.flows.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lj.app.core.common.flows.model.NodeModel;
import com.lj.app.core.common.flows.model.TaskModel;

public class FlowUtilTest {

	@Test
	public void getBaseTest() {
		NodeModel node = new TaskModel();
		String str = FlowUtil.getBase(node);
		System.out.println("str="+ str);
		assertTrue(str.contains("task"));
	}

}
