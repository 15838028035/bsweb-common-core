package com.lj.app.core.common.flows.handlers.impl;

import java.util.concurrent.Callable;

import com.lj.app.core.common.flows.core.Execution;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowProcess;
import com.lj.app.core.common.flows.handlers.IHandler;
import com.lj.app.core.common.flows.model.SubProcessModel;
import com.lj.app.core.common.flows.service.FlowEngine;

/**
 * 启动子流程的处理器
 */
public class StartSubProcessHandler implements IHandler {
	private SubProcessModel model;
	/**
	 * 是否以future方式执行启动子流程任务
	 */
	private boolean isFutureRunning = false;
	public StartSubProcessHandler(SubProcessModel model) {
		this.model = model;
	}
	
	public StartSubProcessHandler(SubProcessModel model, boolean isFutureRunning) {
		this.model = model;
		this.isFutureRunning = isFutureRunning;
	}
	
	/**
	 * 子流程执行的处理
	 */
	public void handle(Execution execution) {
	/*	//根据子流程模型名称获取子流程定义对象
		SnakerEngine engine = execution.getEngine();
		FlowProcess process = engine.process().getProcessByVersion(model.getProcessName(), model.getVersion());
		
		Execution child = execution.createSubExecution(execution, process, model.getName());
		Order order = null;
		if(isFutureRunning) {
			//创建单个线程执行器来执行启动子流程的任务
			ExecutorService es = Executors.newSingleThreadExecutor();
			//提交执行任务，并返回future
			Future<Order> future = es.submit(new ExecuteTask(execution, process, model.getName()));
			try {
				es.shutdown();
				order = future.get();
			} catch (InterruptedException e) {
				throw new SnakerException("创建子流程线程被强制终止执行", e.getCause());
			} catch (ExecutionException e) {
				throw new SnakerException("创建子流程线程执行异常.", e.getCause());
			}
		} else {
			order  = engine.startInstanceByExecution(child);
		}
		AssertHelper.notNull(order, "子流程创建失败");
		execution.addTasks(engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId())));*/
	}

	/**
	 * Future模式的任务执行。通过call返回任务结果集
	 * @author yuqs
	 * @since 1.0
	 */
	class ExecuteTask implements Callable<FlowOrder> {
		private FlowEngine engine;
		private Execution child;
		/**
		 * 构造函数
		 * @param execution
		 * @param process
		 * @param parentNodeName
		 */
		public ExecuteTask(Execution execution, FlowProcess process,String parentNodeName) {
			this.engine = execution.getEngine();
			child = execution.createSubExecution(execution, process, parentNodeName);
		}
		
		public FlowOrder call() throws Exception {
			return engine.startInstanceByExecution(child);
		}
	}
}