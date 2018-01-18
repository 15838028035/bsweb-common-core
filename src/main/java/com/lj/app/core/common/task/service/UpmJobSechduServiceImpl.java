package com.lj.app.core.common.task.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;
import com.lj.app.core.common.task.entity.UpmJob;
import com.lj.app.core.common.task.entity.UpmJobSechdu;
import com.lj.app.core.common.util.SpringContextHolder;

/**
 * 
 * 定时调度服务类
 *
 */
@Service("upmJobSechduService")
public class UpmJobSechduServiceImpl extends BaseServiceImpl implements UpmJobSechduService<UpmJobSechdu> {

  @Autowired
  private UpmJobService<UpmJob> upmJobService;

  @Autowired
  private UpmSchedulerService upmSchedulerService;

  /**
   * 查看当前作业是否处于调度状态
   * 
   * @param jobId jobID
   * @return 是否
   */
  public boolean isProcessJobStatus(Integer jobId) {
    Map<String, Integer> filterMap = new HashMap<String, Integer>();
    filterMap.put("jobId", jobId);

    List<com.lj.app.core.common.task.entity.UpmJobSechdu> list = this.queryForList(filterMap);
    if (list.size() > 0) {
      for (com.lj.app.core.common.task.entity.UpmJobSechdu uapJobSechdu2 : list) {
        String jobStatus = uapJobSechdu2.getJodStatus();
        if ("1".equals(jobStatus)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 查看作业作业是否调度过
   * 
   * @param jobId jobID
   * @return 是否
   */
  public boolean hasJobSechdu(Integer jobId) {
    Map<String, Integer> filterMap = new HashMap<String, Integer>();
    filterMap.put("jobId", jobId);

    List<UpmJobSechdu> list = this.queryForList(filterMap);
    if (list.size() > 0) {
      return true;
    }
    return false;
  }

  /**
   * 作业调度开始
   * 
   * @param jobId  jobID
   * @param jobStartDate 作业开始时间
   *          
   * @return void
   */
  public boolean jobStartSechdued(Integer jobId, Date jobStartDate) {
    try {
      log.warn("...........begin write upm_job_sechdu start info..................." + jobId);
      com.lj.app.core.common.task.entity.UpmJobSechdu upmJobSechdu = 
          new com.lj.app.core.common.task.entity.UpmJobSechdu();
      upmJobSechdu.setJobId(jobId);
      upmJobSechdu.setStartTime(jobStartDate);
      upmJobSechdu.setJodStatus("1");// 1代表执行中；2代表完成
      upmJobSechdu.setIsSuccess(0);
      upmJobSechdu.setResultMsg("");
      this.insertObject(upmJobSechdu);
      log.warn("...........end write upm_job_sechdu start info..................." + jobId);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 作业调度结束
   * 
   * @param jobId jobID
   * @param jobStartDate 作业开始时间
   * @param isSuccess 是否成功
   * @param resultMsg 结果消息
   * @return 是否
   */
  public boolean jobEndSechdued(Integer jobId, Date jobStartDate, Integer isSuccess, String resultMsg)  {
    try {
      log.warn("...........begin write upm_job_sechdu end info.........." + jobId);
      com.lj.app.core.common.task.entity.UpmJobSechdu uapJobSechduCond =
          new com.lj.app.core.common.task.entity.UpmJobSechdu();
      uapJobSechduCond.setJobId(jobId);
      uapJobSechduCond.setStartTime(jobStartDate);

      List<com.lj.app.core.common.task.entity.UpmJobSechdu> upmJobSechduList = this.queryForList(uapJobSechduCond);

      for (com.lj.app.core.common.task.entity.UpmJobSechdu upmJobSechdu : upmJobSechduList) {
        upmJobSechdu.setJodStatus("2");// 1代表执行中；2代表完成
        upmJobSechdu.setEndTime(new Date());
        upmJobSechdu.setIsSuccess(isSuccess);
        upmJobSechdu.setResultMsg(resultMsg);
        this.updateObject(upmJobSechdu);
      }
      log.warn("...........end write upm_job_sechdu end info.........." + jobId);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 执行job任务
   * 
   * @param jobId jobID
   * @throws Exception 异常信息
   */
  @SuppressWarnings("unchecked")
  public void runJob(Integer jobId) throws Exception {
    UpmJob upmJob = (UpmJob) upmJobService.getInfoByKey(jobId);

    log.warn("=====" + upmJob.getJobName());

    Map<String, Integer> filterMap = new HashMap<String, Integer>();
    filterMap.put("jobId", jobId);

    List<com.lj.app.core.common.task.entity.UpmJobSechdu> upmJobSechduList = this.queryForList(filterMap);

    for (com.lj.app.core.common.task.entity.UpmJobSechdu ujs : upmJobSechduList) {
      String jobStatus = ujs.getJodStatus();
      if (jobStatus.equals("1")) {  //1代表执行中；2代表完成
        throw new Exception("任务正在执行中，请稍等...");
      }
    }

    Date jobStartDate = new Date();

    jobStartSechdued(jobId, jobStartDate);

    try {
      Class cls = Class.forName(upmJob.getJobClass());
      BaseTaskService baseTaskService = (BaseTaskService) SpringContextHolder.getBean(cls);
      upmSchedulerService.setCron(upmJob.getJobFrequency());
      upmSchedulerService.setBaseTaskService(baseTaskService);

      jobEndSechdued(jobId, jobStartDate, 1, "执行成功");
    } catch (Exception e) {
      e.printStackTrace();
      log.error("runJob 执行失败,失败原因:" + e.getMessage());
      jobEndSechdued(jobId, jobStartDate, 0, "执行失败,失败原因:" + e.getMessage());
    }
  }
}
