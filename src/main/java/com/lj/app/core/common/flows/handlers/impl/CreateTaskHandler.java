package com.lj.app.core.common.flows.handlers.impl;

import com.lj.app.core.common.flows.core.Execution;
import com.lj.app.core.common.flows.handlers.IHandler;
import com.lj.app.core.common.flows.model.TaskModel;


/**
 * 任务创建操作的处理器
 */
public class CreateTaskHandler implements IHandler {
	/**
	 * 任务模型
	 */
	private TaskModel model;
	
	/**
	 * 调用者需要提供任务模型
	 * @param model 模型
	 */
	public CreateTaskHandler(TaskModel model) {
		this.model = model;
	}
	
	/**
	 * 根据任务模型、执行对象，创建下一个任务，并添加到execution对象的tasks集合中
	 */
	public void handle(Execution execution) {
	/*	List<FlowTask> tasks = execution.getEngine().task().createTask(model, execution);
		execution.addTasks(tasks);
		*//**
		 * 从服务上下文中查找任务拦截器列表，依次对task集合进行拦截处理
		 *//*
		List<SnakerInterceptor> interceptors = ServiceContext.getContext().findList(SnakerInterceptor.class);
		try {
			for(SnakerInterceptor interceptor : interceptors) {
				interceptor.intercept(execution);
			}
		} catch(Exception e) {
			log.error("拦截器执行失败=" + e.getMessage());
			throw new SnakerException(e);
		}*/
	}
}