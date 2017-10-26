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

	/**
	 * 作业调度结束
	 * @param jobId
	 * @param jobStartDate
	 * @param isSuccess
	 * @param resultMsg
	 * @return
	 */
	public boolean JobEndSechdued(Integer jobId,Date jobStartDate,Integer isSuccess,String resultMsg);
	
	/**
	 * 执行job任务
	 * @param jobId
	 * @throws Exception
	 */
	public void runJob(Integer jobId) throws Exception ;
	
}
