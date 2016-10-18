package com.lj.app.core.common.flows.handlers.impl;

import com.lj.app.core.common.flows.core.Execution;
import com.lj.app.core.common.flows.handlers.IHandler;

/**
 * 结束流程实例的处理器
 */
public class EndProcessHandler implements IHandler {
	/**
	 * 结束当前流程实例，如果存在父流程，则触发父流程继续执行
	 */
	public void handle(Execution execution) {
	/*	SnakerEngine engine = execution.getEngine();
		Order order = execution.getOrder();
		List<FlowTask> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
		for(FlowTask task : tasks) {
			if(task.isMajor()) throw new FlowException("存在未完成的主办任务,请确认.");
			engine.task().complete(task.getId(), SnakerEngine.AUTO);
		}
		*//**
		 * 结束当前流程实例
		 *//*
		engine.order().complete(order.getId());
		
		*//**
		 * 如果存在父流程，则重新构造Execution执行对象，交给父流程的SubProcessModel模型execute
		 *//*
		if(StringHelper.isNotEmpty(order.getParentId())) {
			Order parentOrder = engine.query().getOrder(order.getParentId());
			if(parentOrder == null) return;
			Process process = engine.process().getProcessById(parentOrder.getProcessId());
			ProcessModel pm = process.getModel();
			if(pm == null) return;
			SubProcessModel spm = (SubProcessModel)pm.getNode(order.getParentNodeName());
            Execution newExecution = new Execution(engine, process, parentOrder, execution.getArgs());
            newExecution.setChildOrderId(order.getId());
            newExecution.setTask(execution.getTask());
			spm.execute(newExecution);
			*//**
			 * SubProcessModel执行结果的tasks合并到当前执行对象execution的tasks列表中
			 *//*
			execution.addTasks(newExecution.getTasks());
		}
	}*/
	}
}
