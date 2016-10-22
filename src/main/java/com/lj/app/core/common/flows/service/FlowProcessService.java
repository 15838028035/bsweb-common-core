package com.lj.app.core.common.flows.service;

import java.io.InputStream;

import com.lj.app.core.common.base.service.BaseService;

/**
 * @title :流程定义表
 * @description :FlowProcessService
 * @author: liujie
 * @date: 2016-10-17 21:29:05
 */
public interface FlowProcessService<FlowProcess> extends BaseService {

	/**
	 * 检查流程定义对象
	 * @param process 流程定义对象
	 * @param idOrName 流程定义id/name
	 */
	public void check(FlowProcess process, String idOrName);
	
	/**
	 * 保存流程定义
	 * @param process 流程定义对象
	 */
	public void saveFlowProcess(FlowProcess process);
	
	/**
	 * 更新流程定义的类别
	 * @param id 流程定义id
	 * @param type 类别
	 * @since 1.5
	 */
	public void updateType(String id, String type);
	
	/**
	 * 根据主键ID获取流程定义对象
	 * @param id 流程定义id
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessById(String id);
	
	/**
	 * 根据流程name获取流程定义对象
	 * @param name 流程定义名称
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessByName(String name);
	
	/**
	 * 根据流程name、version获取流程定义对象
	 * @param name 流程定义名称
	 * @param version 版本号
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessByVersion(String name, Integer version);
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param input 流程定义输入流
	 * @return String 流程定义id
	 */
	public String deploy(InputStream input);
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param input 流程定义输入流
	 * @param creator 创建人
	 * @return String 流程定义id
	 */
	public String deploy(InputStream input, String creator);
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param id 流程定义id
	 * @param input 流程定义输入流
	 */
	public void redeploy(String id, InputStream input);
	
	/**
	 * 卸载指定的流程定义，只更新状态
	 * @param id 流程定义id
	 */
	public void undeploy(String id);
	
	/**
	 * 谨慎使用.数据恢复非常痛苦，你懂得~~
	 * 级联删除指定流程定义的所有数据：
	 * 1.wf_process
	 * 2.wf_order,wf_hist_order
	 * 3.wf_task,wf_hist_task
	 * 4.wf_task_actor,wf_hist_task_actor
	 * 5.wf_cc_order
	 * @param id
	 */
	public void cascadeRemove(String id);
	
	/**
	 * 获得流程最新版本
	 * @param flowName
	 * @return
	 */
	public int getLatestProcessVersion(String flowName);
}