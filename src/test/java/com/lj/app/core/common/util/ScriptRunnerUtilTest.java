package com.lj.app.core.common.util;

import java.sql.Connection;

import org.junit.Test;

import com.lj.app.core.common.flows.FlowBaseTest;

public class ScriptRunnerUtilTest extends FlowBaseTest{

	@Test
	public void runScriptTest() throws Exception {
		  Connection conn = null;
          ScriptRunnerUtil runner = new ScriptRunnerUtil(conn, true);
          runner.runScript("update_data_test.sql");
	}
}
