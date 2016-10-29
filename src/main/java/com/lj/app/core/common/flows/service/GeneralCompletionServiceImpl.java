package com.lj.app.core.common.flows.service;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.flows.entity.FlowOrderHist;
import com.lj.app.core.common.flows.entity.FlowTaskHist;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Service("flowCompletionService")
public class GeneralCompletionServiceImpl implements FlowCompletionService{
	 private static final Logger log = LoggerFactory.getLogger(GeneralCompletionServiceImpl.class);
	 
	 
	/**
	 * 任务完成触发执行
	 * 
	 * @param task
	 *            任务对象
	 */
	public void complete(FlowTaskHist flowTaskHist){
		 log.debug("The task[{}] has been user[{}] has completed", flowTaskHist.getId(), flowTaskHist.getOperator());
	}

	/**
	 * 实例完成触发执行
	 * 
	 * @param order
	 *            实例对象
	 */
	public void complete(FlowOrderHist flowOrderHist){
		 log.debug("The order[{}] has completed", flowOrderHist.getId());
	}
}
