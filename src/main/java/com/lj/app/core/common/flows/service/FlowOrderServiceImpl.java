package com.lj.app.core.common.flows.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;
import com.lj.app.core.common.flows.entity.FlowProcess;
import com.lj.app.core.common.util.JsonUtil;

/**
 * @title :
 * @description :FlowOrderService
 * @author: liujie
 * @date: 2016-10-18 08:19:32
 */
@Service("flowOrderService")
public class FlowOrderServiceImpl<FlowOrder> extends BaseServiceImpl<FlowOrder> implements FlowOrderService<FlowOrder>{

	/**
	 *
	 * @param process
	 * @param operator
	 * @param args
	 * @param parentId
	 * @param parentNodeName
	 * @return
	 */
	public com.lj.app.core.common.flows.entity.FlowOrder createFlowOrder(FlowProcess process, String operator,Map<String, Object> args, String parentId, String parentNodeName)
	throws Exception{
		 com.lj.app.core.common.flows.entity.FlowOrder flowOrder = new  com.lj.app.core.common.flows.entity.FlowOrder();
		 
		 flowOrder.setFlowProcessId(process.getId().toString());
		 flowOrder.setParentId(parentId);
		 flowOrder.setParentNodeName(parentNodeName);
		 flowOrder.setVariable(JsonUtil.toJson(args));
		 int retKey = this.insertObjectReturnKey(flowOrder);
		 return (com.lj.app.core.common.flows.entity.FlowOrder)this.getInfoByKey(retKey);
		
	}
}
