package com.lj.app.core.common.flows.util;

import com.lj.app.core.common.flows.cfg.Configuration;
import com.lj.app.core.common.flows.service.FlowEngine;

public class FlowUtil {
	private static final FlowEngine flowEngine;
	
	static {
		flowEngine = new Configuration().buildSnakerEngine();
	}
	
	public static FlowEngine getEngine() {
		return flowEngine;
}
}
