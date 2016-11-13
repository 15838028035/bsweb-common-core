package com.lj.app.core.common.flows.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lj.app.core.common.base.service.BaseService;
import com.lj.app.core.common.flows.entity.FlowFormField;

/**
 * @title :
 * @description :FlowFormTableService
 * @author: liujie
 * @date: 2016-11-13 11:37:52
 */
public interface FlowFormTableService<FlowFormTable> extends BaseService {

	 public Map<String, String> process(com.lj.app.core.common.flows.entity.FlowFormTable entity, Map<String, Object> datas);
	 
	 public void submit(long formId, List<FlowFormField> fields, Map<String, Object> params,
             HttpServletRequest request, String processId, String orderId, String taskId) throws Exception;
	 
	 public Map<String, Object> getDataByOrderId(com.lj.app.core.common.flows.entity.FlowFormTable entity, String orderId) ;
	 
	  public List<Map<String, Object>> getDatasByOrderId(com.lj.app.core.common.flows.entity.FlowFormTable entity, String orderId) ;
}
