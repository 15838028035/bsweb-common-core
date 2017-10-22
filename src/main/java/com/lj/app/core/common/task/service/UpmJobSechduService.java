package com.lj.app.core.common.task.service;

import java.util.Date;

import com.lj.app.core.common.base.service.BaseService;

public interface UpmJobSechduService<UpmJobSechdu> extends BaseService {
	/**
	 * 查看当前作业是否处于调度状态
	 * @param jobId
	 * @return
	 */
	public boolean isProcessJobStatus(Integer jobId);
	
	/**
	 * 查看作业作业是否调度过
	 * @param jobId
	 * @return
	 */
	public boolean hasJobSechdu(Integer jobId);
	
	/**
	 * 作业调度开始
     * @param Long jobId,Date jobStartDate
     * @return void
	*/
	public boolean JobStartSechdued(Integer jobId,Date jobStartDate);
	
	public boolean JobEndSechdued(Integer jobId,Date jobStartDate);
	
	@SuppressWarnings("unchecked")
	public void runJob(Integer jobId) throws Exception ;
	
}
