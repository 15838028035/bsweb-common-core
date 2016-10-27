package com.lj.app.core.common.flows.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

import com.lj.app.core.common.flows.Context;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class SpringFlowContext implements Context {
	private static final Logger log = LoggerFactory
			.getLogger(SpringFlowContext.class);
	/**
	 * Spring context
	 */
	private ApplicationContext applicationContext;

	private DefaultListableBeanFactory beanFactory;

	/**
	 * 根据spring的上下文构造SpringContext
	 * 
	 * @param ctx
	 *            上下文
	 */
	public SpringFlowContext(ApplicationContext ctx) {
		this.applicationContext = ctx;
		beanFactory = (DefaultListableBeanFactory) ctx
				.getAutowireCapableBeanFactory();
	}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> clazz) {
		String[] names = applicationContext.getBeanNamesForType(clazz);
		if (names.length > 1 && log.isWarnEnabled()) {
			log.warn("重复定义类型:" + clazz);
		}

		if (names.length >= 1) {
			return (T) applicationContext.getBean(names[0]);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findList(Class<T> clazz) {
		String[] names = applicationContext.getBeanNamesForType(clazz);
		List<T> beans = new ArrayList<T>();
		for (String name : names) {
			beans.add((T) applicationContext.getBean(name));
		}
		return beans;
	}

	public <T> T findByName(String name, Class<T> clazz) {
		return applicationContext.getBean(name, clazz);
	}

	/**
	 * spring不支持向applicationContext中直接添加对象
	 */
	public void put(String name, Object object) {
		log.warn("spring不支持向applicationContext中直接添加对象");
	}

	/**
	 * 向spring添加bean的定义
	 */
	public void put(String name, Class<?> clazz) {
		BeanDefinition definition = new RootBeanDefinition(clazz);
		beanFactory.registerBeanDefinition(name, definition);
	}

	/**
	 * 判断是否存在服务
	 */
	public boolean exist(String name) {
		return applicationContext.containsBean(name);
	}

}
