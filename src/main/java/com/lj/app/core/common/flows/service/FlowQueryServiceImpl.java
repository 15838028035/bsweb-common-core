package com.lj.app.core.common.flows.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;

@Service("flowQueryService")
public class FlowQueryServiceImpl implements FlowQueryService {
	
	@Autowired
	private FlowTaskService<FlowTask> flowTaskService;
	
	public FlowOrder getFlowOrder(String orderId){
		return null;
	}
	
	public List<FlowTask> getActiveTasks(Integer flowOrderId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flowOrderId", flowOrderId);
		
		return flowTaskService.queryForList(map);
	}
}
