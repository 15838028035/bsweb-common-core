package com.lj.app.core.common.flows.spring;

import org.springframework.context.ApplicationContext;

import com.lj.app.core.common.exception.FlowException;
import com.lj.app.core.common.flows.cfg.Configuration;

public class SpringConfiguration extends Configuration {
	/**
	 * Spring上下文
	 */
	private ApplicationContext applicationContext;
	
	public SpringConfiguration(ApplicationContext ctx) {
		super(new SpringFlowContext(ctx));
		this.applicationContext = ctx;
	}
	
	public void parser() throws FlowException {
		super.parser();
	}
	
	public boolean isCMB() {
		return true;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
}

}
