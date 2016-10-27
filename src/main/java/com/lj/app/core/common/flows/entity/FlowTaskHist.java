package com.lj.app.core.common.flows.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

/**
*流程任务表
*/
public class FlowTaskHist extends BaseEntity{
	
	/**
	 * ID  id
	 */
	private java.lang.Integer id;
	
	/**
	 * 版本  TASK_VEFRSION
	 */
	private java.lang.Integer taskVefrsion;
	
	/**
	 * 流程实例ID  FLOW_ORDER_ID
	 */
	private java.lang.Integer flowOrderId;
	
	/**
	 * 任务名称  TASK_NAME
	 */
	private String taskName;
	
	/**
	 * 显示名称  display_Name
	 */
	private String displayName;
	
	/**
	 * 参与方式（0：普通任务；1：参与者会签任务）  perform_Type
	 */
	private java.lang.Integer performType;
	
	/**
	 * 任务类型（0：主办任务；1：协办任务）  task_Type
	 */
	private java.lang.Integer taskType;
	
	/**
	 * 任务处理者ID  operator
	 */
	private String operator;
	
	/**
	 * 任务创建时间  create_Time
	 */
	private java.util.Date createTime;
	
	 /**
	 * 任务创建时间Begin
	 */
	private String  createTimeBegin;
	/**
	 * 任务创建时间End
	 */
	private String createTimeEnd;
	/**
	 * 任务完成时间  finish_Time
	 */
	private java.util.Date finishTime;
	
	 /**
	 * 任务完成时间Begin
	 */
	private String  finishTimeBegin;
	/**
	 * 任务完成时间End
	 */
	private String finishTimeEnd;
	/**
	 * 期望任务完成时间  expire_Time
	 */
	private java.util.Date expireTime;
	
	 /**
	 * 期望任务完成时间Begin
	 */
	private String  expireTimeBegin;
	/**
	 * 期望任务完成时间End
	 */
	private String expireTimeEnd;
	/**
	 * 期望的完成时间date类型  expire_Date
	 */
	private java.util.Date expireDate;
	
	 /**
	 * 期望的完成时间date类型Begin
	 */
	private String  expireDateBegin;
	/**
	 * 期望的完成时间date类型End
	 */
	private String expireDateEnd;
	/**
	 * 提醒时间date类型  remind_Date
	 */
	private java.util.Date remindDate;
	
	 /**
	 * 提醒时间date类型Begin
	 */
	private String  remindDateBegin;
	/**
	 * 提醒时间date类型End
	 */
	private String remindDateEnd;
	/**
	 * 任务关联的表单url  action_Url
	 */
	private String actionUrl;
	
	/**
	 * 任务参与者列表  actorIds
	 */
	private String actorIds;
	
	/**
	 * 父任务Id  parent_Task_Id
	 */
	private java.lang.Integer parentTaskId;
	
	/**
	 * 任务附属变量  variable
	 */
	private String variable;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getTaskVefrsion() {
		return taskVefrsion;
	}

	public void setTaskVefrsion(java.lang.Integer taskVefrsion) {
		this.taskVefrsion = taskVefrsion;
	}

	public java.lang.Integer getFlowOrderId() {
		return flowOrderId;
	}

	public void setFlowOrderId(java.lang.Integer flowOrderId) {
		this.flowOrderId = flowOrderId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public java.lang.Integer getPerformType() {
		return performType;
	}

	public void setPerformType(java.lang.Integer performType) {
		this.performType = performType;
	}

	public java.lang.Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(java.lang.Integer taskType) {
		this.taskType = taskType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public java.util.Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getFinishTimeBegin() {
		return finishTimeBegin;
	}

	public void setFinishTimeBegin(String finishTimeBegin) {
		this.finishTimeBegin = finishTimeBegin;
	}

	public String getFinishTimeEnd() {
		return finishTimeEnd;
	}

	public void setFinishTimeEnd(String finishTimeEnd) {
		this.finishTimeEnd = finishTimeEnd;
	}

	public java.util.Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(java.util.Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getExpireTimeBegin() {
		return expireTimeBegin;
	}

	public void setExpireTimeBegin(String expireTimeBegin) {
		this.expireTimeBegin = expireTimeBegin;
	}

	public String getExpireTimeEnd() {
		return expireTimeEnd;
	}

	public void setExpireTimeEnd(String expireTimeEnd) {
		this.expireTimeEnd = expireTimeEnd;
	}

	public java.util.Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getExpireDateBegin() {
		return expireDateBegin;
	}

	public void setExpireDateBegin(String expireDateBegin) {
		this.expireDateBegin = expireDateBegin;
	}

	public String getExpireDateEnd() {
		return expireDateEnd;
	}

	public void setExpireDateEnd(String expireDateEnd) {
		this.expireDateEnd = expireDateEnd;
	}

	public java.util.Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(java.util.Date remindDate) {
		this.remindDate = remindDate;
	}

	public String getRemindDateBegin() {
		return remindDateBegin;
	}

	public void setRemindDateBegin(String remindDateBegin) {
		this.remindDateBegin = remindDateBegin;
	}

	public String getRemindDateEnd() {
		return remindDateEnd;
	}

	public void setRemindDateEnd(String remindDateEnd) {
		this.remindDateEnd = remindDateEnd;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getActorIds() {
		return actorIds;
	}

	public void setActorIds(String actorIds) {
		this.actorIds = actorIds;
	}

	public java.lang.Integer getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(java.lang.Integer parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
	
}

