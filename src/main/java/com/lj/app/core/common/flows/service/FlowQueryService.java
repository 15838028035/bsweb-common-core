package com.lj.app.core.common.flows.service;

import java.util.List;

import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;


public interface FlowQueryService {

	public FlowOrder getFlowOrder(String orderId);

	public List<FlowTask> getActiveTasks(Integer flowOrderId);

}
