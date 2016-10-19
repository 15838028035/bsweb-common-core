package com.lj.app.core.common.flows.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.flows.cfg.Configuration;
import com.lj.app.core.common.flows.core.Execution;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowProcess;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.flows.model.NodeModel;
import com.lj.app.core.common.flows.model.ProcessModel;
import com.lj.app.core.common.flows.model.StartModel;
import com.lj.app.core.common.flows.model.TaskModel;
import com.lj.app.core.common.util.Assert;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * 基本的流程引擎实现类
 */
@Service("flowEngine")
public class FlowEngineImpl implements FlowEngine {

	private static final Logger log = LoggerFactory.getLogger(FlowEngineImpl.class);
			
	/**
	 * Snaker配置对象
	 */
	protected Configuration configuration;
	/**
	 * 流程定义业务类
	 */
	@Autowired
	protected FlowProcessService flowProcessService;
	/**
	 * 流程实例业务类
	 */
	@Autowired
	protected FlowOrderService flowOrderService;
	/**
	 * 任务业务类
	 */
	@Autowired
	protected FlowTaskService flowTaskService;
	/**
	 * 查询业务类
	 */
	@Autowired
	protected FlowQueryService flowQueryService;
	/**
	 * 管理业务类
	 */
	@Autowired
	protected FlowManagerService flowManagerService;

	/**
	 * 根据serviceContext上下文，查找processService、orderService、taskService服务
	 */
	public FlowEngine configure(Configuration config) {
		this.configuration = config;
		return this;
	}

	/**
	 * 根据流程定义ID启动流程实例
	 */
	public FlowOrder startInstanceById(String id) {
		return startInstanceById(id, null, null);
	}

	/**
	 * 根据流程定义ID，操作人ID启动流程实例
	 */
	public FlowOrder startInstanceById(String id, String operator) {
		return startInstanceById(id, operator, null);
	}

	/**
	 * 根据流程定义ID，操作人ID，参数列表启动流程实例
	 */
	public FlowOrder startInstanceById(String id, String operator,
			Map<String, Object> args) {
		if (args == null)
			args = new HashMap<String, Object>();
		FlowProcess process = (FlowProcess)flowProcessService().getProcessById(id);
		flowProcessService().check(process, id);
		return startProcess(process, operator, args);
	}

	/**
	 * 根据流程名称启动流程实例
	 */
	public FlowOrder startInstanceByName(String name) {
		return startInstanceByName(name, null, null, null);
	}

	/**
	 * 根据流程名称、版本号启动流程实例
	 */
	public FlowOrder startInstanceByName(String name, Integer version) {
		return startInstanceByName(name, version, null, null);
	}

	/**
	 * 根据流程名称、版本号、操作人启动流程实例
	 */
	public FlowOrder startInstanceByName(String name, Integer version,
			String operator) {
		return startInstanceByName(name, version, operator, null);
	}

	/**
	 * 根据流程名称、版本号、操作人、参数列表启动流程实例
	 */
	public FlowOrder startInstanceByName(String name, Integer version,
			String operator, Map<String, Object> args) {
		if (args == null)
			args = new HashMap<String, Object>();
		FlowProcess process = (FlowProcess) flowProcessService().getProcessByVersion(name, version);
		flowProcessService().check(process, name);
		return startProcess(process, operator, args);
	}

	private FlowOrder startProcess(FlowProcess process, String operator,
			Map<String, Object> args) {
		Execution execution = execute(process, operator, args, null, null);
		if (process.getModel() != null) {
			StartModel start = process.getModel().getStart();
			Assert.notNull(start, "流程定义[name=" + process.getFlowName()
					+ ", version=" + process.getFlowVersion() + "]没有开始节点");
			start.execute(execution);
		}

		return execution.getFlowOrder();
	}

	/**
	 * 根据父执行对象启动子流程实例（用于启动子流程）
	 */
	public FlowOrder startInstanceByExecution(Execution execution) {
		FlowProcess process = execution.getProcess();
		StartModel start = process.getModel().getStart();
		Assert.notNull(start, "流程定义[id=" + process.getId() + "]没有开始节点");

		Execution current = execute(process, execution.getOperator(),
				execution.getArgs(), String.valueOf(execution.getParentOrder().getId()),
				execution.getParentNodeName());
		start.execute(current);
		return current.getFlowOrder();
	}

	/**
	 * 创建流程实例，并返回执行对象
	 * 
	 * @param process
	 *            流程定义
	 * @param operator
	 *            操作人
	 * @param args
	 *            参数列表
	 * @param parentId
	 *            父流程实例id
	 * @param parentNodeName
	 *            启动子流程的父流程节点名称
	 * @return Execution
	 */
	private Execution execute(FlowProcess process, String operator,
			Map<String, Object> args, String parentId, String parentNodeName) {
		FlowOrder order =(FlowOrder) flowOrderService().createFlowOrder(process, operator, args, parentId,
				parentNodeName);
		if (log.isDebugEnabled()) {
			log.debug("创建流程实例对象:" + order);
		}
		Execution current = new Execution(this, process, order, args);
		current.setOperator(operator);
		return current;
	}

	/**
	 * 根据任务主键ID执行任务
	 */
	public List<FlowTask> executeTask(String taskId) {
		return executeTask(taskId, null);
	}

	/**
	 * 根据任务主键ID，操作人ID执行任务
	 */
	public List<FlowTask> executeTask(String taskId, String operator) {
		return executeTask(taskId, operator, null);
	}

	/**
	 * 根据任务主键ID，操作人ID，参数列表执行任务
	 */
	public List<FlowTask> executeTask(String taskId, String operator,
			Map<String, Object> args) {
		// 完成任务，并且构造执行对象
		Execution execution = execute(taskId, operator, args);
		if (execution == null)
			return Collections.emptyList();
		ProcessModel model = execution.getProcess().getModel();
		if (model != null) {
			NodeModel nodeModel = model.getNode(execution.getTask()
					.getTaskName());
			// 将执行对象交给该任务对应的节点模型执行
			nodeModel.execute(execution);
		}
		return execution.getTasks();
	}

	/**
	 * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点 1、nodeName为null时，则驳回至上一步处理
	 * 2、nodeName不为null时，则任意跳转，即动态创建转移
	 */
	public List<FlowTask> executeAndJumpTask(String taskId, String operator,
			Map<String, Object> args, String nodeName) {
		/*Execution execution = execute(taskId, operator, args);
		if (execution == null)
			return Collections.emptyList();
		ProcessModel model = execution.getProcess().getModel();
		Assert.notNull(model, "当前任务未找到流程定义模型");
		if (StringUtil.isBlank(nodeName)) {
			FlowTask newTask = task().rejectTask(model, execution.getTask());
			execution.addTask(newTask);
		} else {
			NodeModel nodeModel = model.getNode(nodeName);
			Assert.notNull(nodeModel, "根据节点名称[" + nodeName + "]无法找到节点模型");
			// 动态创建转移对象，由转移对象执行execution实例
			TransitionModel tm = new TransitionModel();
			tm.setTarget(nodeModel);
			tm.setEnabled(true);
			tm.execute(execution);
		}

		return execution.getTasks();*/
		return null;
	}

	/**
	 * 根据流程实例ID，操作人ID，参数列表按照节点模型model创建新的自由任务
	 */
	public List<FlowTask> createFreeTask(String orderId, String operator,
			Map<String, Object> args, TaskModel model) {
	/*	FlowOrder order = query().getFlowOrder(orderId);
		Assert.notNull(order, "指定的流程实例[id=" + orderId + "]已完成或不存在");
		order.setLastUpdator(operator);
		order.setLastUpdateTime(DateHelper.getTime());
		Process process = process().getProcessById(order.getProcessId());
		Execution execution = new Execution(this, process, order, args);
		execution.setOperator(operator);
		return task().createTask(model, execution);*/
		return null;
	}

	/**
	 * 根据任务主键ID，操作人ID，参数列表完成任务，并且构造执行对象
	 * 
	 * @param taskId
	 *            任务id
	 * @param operator
	 *            操作人
	 * @param args
	 *            参数列表
	 * @return Execution
	 */
	private Execution execute(String taskId, String operator,
			Map<String, Object> args) {
		/*if (args == null)
			args = new HashMap<String, Object>();
		FlowTask task = task().complete(taskId, operator, args);
		if (log.isDebugEnabled()) {
			log.debug("任务[taskId=" + taskId + "]已完成");
		}
		FlowOrder order = query().getOrder(task.getOrderId());
		Assert.notNull(order, "指定的流程实例[id=" + task.getOrderId()
				+ "]已完成或不存在");
		order.setLastUpdator(operator);
		order.setLastUpdateTime(DateHelper.getTime());
		order().updateOrder(order);
		// 协办任务完成不产生执行对象
		if (!task.isMajor()) {
			return null;
		}
		Map<String, Object> orderMaps = order.getVariableMap();
		if (orderMaps != null) {
			for (Map.Entry<String, Object> entry : orderMaps.entrySet()) {
				if (args.containsKey(entry.getKey())) {
					continue;
				}
				args.put(entry.getKey(), entry.getValue());
			}
		}
		Process process = process().getProcessById(order.getProcessId());
		Execution execution = new Execution(this, process, order, args);
		execution.setOperator(operator);
		execution.setTask(task);
		return execution;*/
		return null;
	}


	public Configuration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}


	public FlowProcessService getFlowProcessService() {
		return flowProcessService;
	}

	public void setFlowProcessService(FlowProcessService flowProcessService) {
		this.flowProcessService = flowProcessService;
	}

	public FlowOrderService getFlowOrderService() {
		return flowOrderService;
	}

	public void setFlowOrderService(FlowOrderService flowOrderService) {
		this.flowOrderService = flowOrderService;
	}

	public FlowTaskService getFlowTaskService() {
		return flowTaskService;
	}

	public void setFlowTaskService(FlowTaskService flowTaskService) {
		this.flowTaskService = flowTaskService;
	}

	public FlowQueryService getFlowQueryService() {
		return flowQueryService;
	}

	public void setFlowQueryService(FlowQueryService flowQueryService) {
		this.flowQueryService = flowQueryService;
	}

	public FlowManagerService getFlowManagerService() {
		return flowManagerService;
	}

	public void setFlowManagerService(FlowManagerService flowManagerService) {
		this.flowManagerService = flowManagerService;
	}

	@Override
	public FlowProcessService flowProcessService() {
		return flowProcessService;
	}

	@Override
	public FlowQueryService flowQueryService() {
		return flowQueryService;
	}

	@Override
	public FlowOrderService flowOrderService() {
		return flowOrderService;
	}

	@Override
	public FlowTaskService flowTaskService() {
		return flowTaskService;
	}

	@Override
	public FlowManagerService flowManagerService() {
		return flowManagerService;
	}

}
