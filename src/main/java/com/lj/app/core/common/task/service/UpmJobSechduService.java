package com.lj.app.core.common.task.service;

import java.util.Date;

import com.lj.app.core.common.base.service.BaseService;


/**
 * 
 * 定时调度服务类
 * @param  <UpmJobSechdu> 调度对象
 */
public interface UpmJobSechduService<UpmJobSechdu> extends BaseService {
  /**
   * 查看当前作业是否处于调度状态
   * 
   * @param jobId jobID
   * @return 是否
   */
  public boolean isProcessJobStatus(Integer jobId);

  /**
   * 查看作业作业是否调度过
   * 
   * @param jobId jobID
   * @return 是否
   */
  public boolean hasJobSechdu(Integer jobId);

  /**
   * 作业调度开始
   * 
   * @param jobId  jobID
   * @param jobStartDate 作业开始时间
   *          
   * @return void
   */
  public boolean jobStartSechdued(Integer jobId, Date jobStartDate);

  /**
   * 作业调度结束
   * 
   * @param jobId jobID
   * @param jobStartDate 作业开始时间
   * @param isSuccess 是否成功
   * @param resultMsg 结果消息
   * @return 是否
   */
  public boolean jobEndSechdued(Integer jobId, Date jobStartDate, Integer isSuccess, String resultMsg);

  /**
   * 执行job任务
   * 
   * @param jobId jobID
   * @throws Exception 异常信息
   */
  public void runJob(Integer jobId) throws Exception;

}
