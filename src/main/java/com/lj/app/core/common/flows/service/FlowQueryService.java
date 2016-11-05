package com.lj.app.core.common.flows.service;

import java.util.List;

import com.lj.app.core.common.flows.entity.FlowApprove;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowOrderHist;
import com.lj.app.core.common.flows.entity.FlowQueryFilter;
import com.lj.app.core.common.flows.entity.FlowTask;
import com.lj.app.core.common.flows.entity.FlowTaskHist;
import com.lj.app.core.common.flows.entity.FlowWorkItem;
import com.lj.app.core.common.pagination.Page;


public interface FlowQueryService {

	public FlowOrder getFlowOrder(String orderId);

	public List<FlowTask> getActiveTasks(String flowOrderId);
	public List<FlowTask> getActiveTasks(int flowOrderId);

	public FlowTask getFlowTask(String taskId);

	public List<FlowApprove> queryApprove(String orderId, String taskId)throws Exception;

	public FlowOrderHist getHistOrder(String orderId);

	public List<FlowTaskHist> getHistoryTasks(String orderId);
	
	public List<FlowTaskHist> getHistoryTasks(String orderId, String taskName);

	/**
	 * 根据filter分页查询工作项（包含process、order、task三个实体的字段集合）
	 * @param page 分页对象
	 * @param filter 查询过滤器
	 * @return List<WorkItem> 活动工作项集合
	 */
	public List<FlowWorkItem> getWorkItems(Page<FlowWorkItem> page, FlowQueryFilter filter);
}
