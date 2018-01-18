package com.lj.app.core.common.task.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

/**
 * 测试定时任务
 */
@Service("taskTestService")
public class TaskTestService extends BaseServiceImpl<T> implements BaseTaskService {

  @Override
  @Scheduled(cron = "0 * */1 * * ? ") // 每1秒执行一次
  public void doRunTask() throws Exception {
    log.warn("taskTestService  doRunTask ");
  }
}
