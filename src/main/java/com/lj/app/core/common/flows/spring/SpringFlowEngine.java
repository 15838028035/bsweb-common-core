package com.lj.app.core.common.flows.spring;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lj.app.core.common.flows.service.FlowEngineImpl;

public class SpringFlowEngine extends FlowEngineImpl implements
		InitializingBean, ApplicationContextAware {
	private ApplicationContext applicationContext;
	private Properties properties;

	public void afterPropertiesSet() throws Exception {
		SpringConfiguration configuration = new SpringConfiguration(
				applicationContext);
		if (properties != null)
			configuration.initProperties(properties);
		configuration.parser();
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
