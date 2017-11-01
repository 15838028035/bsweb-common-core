package com.lj.app.core.common.task.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

@Service("upmSchedulerService")
public class UpmSchedulerService extends BaseServiceImpl<T> implements SchedulingConfigurer{
	
	  public static String cron = "0/2 * * * * ?";
	  
	  private BaseTaskService baseTaskService;//执行类的
	  
	  int i =0;
	  @Override
	  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	    taskRegistrar.addTriggerTask(new Runnable() {
	      @Override
	      public void run() {
	        i++;
	        // 需要实现的任务逻辑  
	        if(baseTaskService!=null){
	        	try{
	        	baseTaskService.doRunTask();
	        	}catch(Exception e) {
	        		e.getMessage();
	        		log.error("upmSchedulerService run " +baseTaskService.getClass().getName() + "error, error msg: "  + e.getMessage());
	        	}
	        }
	        
	        log.warn("第"+(i)+"次开始执行操作... " +"时间：【"
	        + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()) 
	        + "】"); 
	      }
	    }, new Trigger(){ 
	      @Override
	      public Date nextExecutionTime(TriggerContext triggerContext) { 
	        //任务触发，可修改任务的执行周期  
	    	  if(baseTaskService!=null){
		        CronTrigger trigger = new CronTrigger(cron); 
		        Date nextExec = trigger.nextExecutionTime(triggerContext); 
		        return nextExec;  
	    	  }
	    	  return null;
	      } 
	    }); 
	  }
	  
	public static String getCron() {
		return cron;
	}
	public static void setCron(String cron) {
		UpmSchedulerService.cron = cron;
	}
	public BaseTaskService getBaseTaskService() {
		return baseTaskService;
	}
	public void setBaseTaskService(BaseTaskService baseTaskService) {
		this.baseTaskService = baseTaskService;
	}

}
