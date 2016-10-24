package com.lj.app.core.common.flows.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;
import com.lj.app.core.common.exception.FlowException;
import com.lj.app.core.common.flows.FlowConstains;
import com.lj.app.core.common.flows.model.ProcessModel;
import com.lj.app.core.common.flows.parser.ModelParser;
import com.lj.app.core.common.util.Assert;
import com.lj.app.core.common.util.DateUtil;
import com.lj.app.core.common.util.FileUtil;
import com.lj.app.core.common.util.StringUtil;

/**
 * @title :流程定义表
 * @description :FlowProcessService
 * @author: liujie
 * @date: 2016-10-17 21:29:05
 */
@Service("flowProcessService")
public class FlowProcessServiceImpl<FlowProcess> extends BaseServiceImpl<FlowProcess> implements FlowProcessService<FlowProcess>{

	/**
	 * 检查流程定义对象
	 * @param process 流程定义对象
	 * @param idOrName 流程定义id/name
	 */
	public void check(FlowProcess process, String idOrName){
		
	}
	
	/**
	 * 保存流程定义
	 * @param process 流程定义对象
	 */
	public void saveFlowProcess(FlowProcess process) throws Exception{
		this.insertObject(process);
	}
	
	/**
	 * 更新流程定义的类别
	 * @param id 流程定义id
	 * @param type 类别
	 * @since 1.5
	 */
	public void updateType(String id, String type)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("flowType", type);
		this.updateObject(map);
	}
	
	/**
	 * 根据主键ID获取流程定义对象
	 * @param id 流程定义id
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessById(String id) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		return (FlowProcess)this.getInfoByKey(id);
	}
	
	/**
	 * 根据流程name获取流程定义对象
	 * @param name 流程定义名称
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessByName(String name) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("flowName", name);
		List list = this.queryForList("select",map);
		if(list!=null && list.size()>0) {
			return (FlowProcess)list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据流程name、version获取流程定义对象
	 * @param name 流程定义名称
	 * @param version 版本号
	 * @return Process 流程定义对象
	 */
	public FlowProcess getProcessByVersion(String name, Integer version)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flowName", name);
		map.put("flowVersion", version);
		List list = this.queryForList("select",map);
		if(list!=null && list.size()>0) {
			return (FlowProcess)list.get(0);
		}
		return null;
	}
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param input 流程定义输入流
	 * @return String 流程定义id
	 */
	public String deploy(InputStream input){
		return deploy(input, null);
	}
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param input 流程定义输入流
	 * @param creator 创建人
	 * @return String 流程定义id
	 */
	public String deploy(InputStream input, String creator){
		Assert.notNull(input);
		try {
			byte[] bytes = FileUtil.readBytes(input);
			ProcessModel model = ModelParser.parse(bytes);
			Integer version = getLatestProcessVersion(model.getName());
			com.lj.app.core.common.flows.entity.FlowProcess entity = new com.lj.app.core.common.flows.entity.FlowProcess();
			entity.setFlowNo(StringUtil.getUUIDKey());
			if(version == null || version < 0) {
				entity.setFlowVersion(0);
			} else {
				entity.setFlowVersion(version + 1);
			}
			entity.setLockStatus(FlowConstains.STATE_ACTIVE.toString());
			entity.setModel(model);
			entity.setFlowContent(bytes.toString());
			entity.setCreateDate(DateUtil.getNowDateYYYYMMddHHMMSS());
			//entity.setCreateBy(creator);
			insertObject(entity);
			//cache(entity);
			return entity.getId().toString();
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new FlowException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * 根據InputStream輸入流，部署流程定义
	 * @param id 流程定义id
	 * @param input 流程定义输入流
	 */
	public void redeploy(String id, InputStream input){
		/*Assert.notNull(input);
		FlowProcess entity = this.getProcessById(id);
		Assert.notNull(entity);
		try {
			byte[] bytes = FileUtil.readBytes(input);
			ProcessModel model = ModelParser.parse(bytes);
			String oldProcessName = entity.getName();
			entity.setModel(model);
			entity.setBytes(bytes);
			this.updateObject(entity);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new FlowException(e.getMessage(), e.getCause());
		}*/
	}
	
	/**
	 * 卸载指定的流程定义，只更新状态
	 * @param id 流程定义id
	 */
	public void undeploy(String id){
		/*FlowProcess flowProcess = this.getProcessById(id);
		flowProcess.setState(FlowConstains.STATE_FINISH);
		this.updateObject(flowProcess);*/
	}
	
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
	public void cascadeRemove(String id){
		
	}
	
	/**
	 * 获得流程最新版本
	 * @param flowName
	 * @return
	 */
	public int getLatestProcessVersion(String flowName)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("flowName", flowName);
		List list = this.queryForList("select",map);
		if(list!=null && list.size()>0) {
			return ((com.lj.app.core.common.flows.entity.FlowProcess)list.get(0)).getFlowVersion();
		}
		return 0;
	}
}
