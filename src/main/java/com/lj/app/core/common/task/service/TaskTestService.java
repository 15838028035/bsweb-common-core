package com.lj.app.core.common.task.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lj.app.core.common.base.service.BaseServiceImpl;

@Component("taskTestService")
public class TaskTestService extends BaseServiceImpl<T> implements BaseTaskService{

	@Override
	@Scheduled(cron="0 */60 * * * ? ")   //每1秒执行一次  
	public void doRunTask() {
		log.warn("taskTestService  doRunTask ");
	}
}
