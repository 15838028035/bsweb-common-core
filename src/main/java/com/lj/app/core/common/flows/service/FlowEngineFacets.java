package com.lj.app.core.common.flows.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.flows.cfg.Configuration;
import com.lj.app.core.common.flows.entity.FlowOrder;
import com.lj.app.core.common.flows.entity.FlowTask;

@Service("flowEngineFacets")
public class FlowEngineFacets {
	
	private FlowEngine flowEngine;
	
	public  FlowEngine getEngine() {
		if(flowEngine==null) {
			flowEngine= new Configuration().buildSnakerEngine();
		}
		return flowEngine;
	}
	
	public FlowOrder startInstanceById(String processId, String operator, Map<String, Object> args) throws Exception{
		return getEngine().startInstanceById(processId, operator, args);
	}
	
	public FlowOrder startInstanceByName(String name, Integer version, String operator, Map<String, Object> args) throws Exception{
		return getEngine().startInstanceByName(name, version, operator, args);
	}
	
	public FlowOrder startAndExecute(String name, Integer version, String operator, Map<String, Object> args)  throws Exception{
		FlowOrder order = getEngine().startInstanceByName(name, version, operator, args);
		List<FlowTask> tasks = getEngine().flowQueryService().getActiveTasks(order.getId());
		List<FlowTask> newTasks = new ArrayList<FlowTask>();
		if(tasks != null && tasks.size() > 0) {
			FlowTask task = tasks.get(0);
			newTasks.addAll(getEngine().executeTask(task.getId().toString(), operator, args));
		}
		return order;
	}
	
	public FlowOrder startAndExecute(String processId, String operator, Map<String, Object> args) throws Exception {
		FlowOrder order = getEngine().startInstanceById(processId, operator, args);
		List<FlowTask> tasks = getEngine().flowQueryService().getActiveTasks(order.getId());
		List<FlowTask> newTasks = new ArrayList<FlowTask>();
		if(tasks != null && tasks.size() > 0) {
			FlowTask task = tasks.get(0);
			newTasks.addAll(getEngine().executeTask(task.getId().toString(), operator, args));
		}
		return order;
	}
	
	public List<FlowTask> execute(String taskId, String operator, Map<String, Object> args) throws Exception{
		return getEngine().executeTask(taskId, operator, args);
	}
	
	public List<FlowTask> executeAndJump(String taskId, String operator, Map<String, Object> args, String nodeName) throws Exception {
		return getEngine().executeAndJumpTask(taskId, operator, args, nodeName);
	}

    public List<FlowTask> transferMajor(String taskId, String operator, String... actors) {
       /* List<FlowTask> tasks = flowEngine.flowTaskService().createNewTask(taskId, TaskType.Major.ordinal(), actors);
        flowEngine.flowTaskService().complete(taskId, operator);
        return tasks;*/
    	return null;
    }

    public List<FlowTask> transferAidant(String taskId, String operator, String... actors) {
      /*  List<FlowTask> tasks = flowEngine.task().createNewTask(taskId, TaskType.Aidant.ordinal(), actors);
        flowEngine.task().complete(taskId, operator);
        return tasks;*/
    	return null;
    }
    
}
