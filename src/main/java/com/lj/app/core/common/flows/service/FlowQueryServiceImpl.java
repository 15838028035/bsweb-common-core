package com.lj.app.core.common.flows.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.flows.entity.FlowApprove;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowOrderHist;
import com.lj.app.core.common.flows.entity.FlowQueryFilter;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.flows.entity.FlowTaskHist;
import com.lj.app.core.common.flows.entity.FlowWorkItem;
import com.lj.app.core.common.pagination.Page;

@Service("flowQueryService")
public class FlowQueryServiceImpl implements FlowQueryService {
	
	@Autowired
	private FlowOrderService<FlowOrder> flowOrderService;
	@Autowired
	private FlowOrderHistService<FlowOrderHist> flowOrderHistService;
	@Autowired
	private FlowTaskService<FlowTask> flowTaskService;
	@Autowired
	private FlowTaskHistService<FlowTask> flowTaskHistService;
	@Autowired
	private FlowApproveService flowApproveService;
	
	public FlowOrder getFlowOrder(String orderId){
		return (FlowOrder)flowOrderService.getInfoByKey(orderId);
	}
	
	public List<FlowTask> getActiveTasks(String flowOrderId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flowOrderId", flowOrderId);
		return flowTaskService.queryForList(map);
	}
	
	public List<FlowTask> getActiveTasks(int flowOrderId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flowOrderId", flowOrderId);
		return flowTaskService.queryForList(map);
	}
	
	public FlowTask getFlowTask(String taskId){
		return (FlowTask) flowTaskService.getInfoByKey(taskId);
	}
	
	public List<FlowApprove> queryApprove(String orderId, String taskId)throws Exception{
		FlowApprove flowApprove = new FlowApprove();
		flowApprove.setOrderId(Integer.parseInt(orderId));
		flowApprove.setTaskId(Integer.parseInt(taskId));
		return flowApproveService.queryForList(flowApprove);
	}
	
	public FlowOrderHist getHistOrder(String orderId){
		return (FlowOrderHist)flowOrderHistService.getInfoByKey(orderId);
	}

	public List<FlowTaskHist> getHistoryTasks(String orderId){
		FlowTaskHist flowTaskHist = new FlowTaskHist();
		flowTaskHist.setFlowOrderId(Integer.parseInt(orderId));
		return flowTaskHistService.queryForList(flowTaskHist);
	}
	
	public List<FlowTaskHist> getHistoryTasks(String orderId, String taskName){
		FlowTaskHist flowTaskHist = new FlowTaskHist();
		flowTaskHist.setFlowOrderId(Integer.parseInt(orderId));
		flowTaskHist.setTaskName(taskName);
		return flowTaskHistService.queryForList(flowTaskHist);
	}
	
	/**
	 * 根据filter分页查询工作项（包含process、order、task三个实体的字段集合）
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项集合
	 */
	public List<FlowWorkItem> getWorkItems(Page<FlowWorkItem> page, FlowQueryFilter filter){
		return null;
	}
}
