package com.lj.app.core.common.task.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

/**
*UpmJobSechdu111
*/
public class UpmJobSechdu extends BaseEntity{
	
	/**
	 * ID  id
	 */
	private java.lang.Integer id;
	
	/**
	 * 任务ID  JOB_ID
	 */
	private java.lang.Integer jobId;
	
	/**
	 * 开始时间  START_TIME
	 */
	private java.util.Date startTime;
	
	 /**
	 * 开始时间Begin
	 */
	private String  startTimeBegin;
	/**
	 * 开始时间End
	 */
	private String startTimeEnd;
	/**
	 * 结束时间  END_TIME
	 */
	private java.util.Date endTime;
	
	 /**
	 * 结束时间Begin
	 */
	private String  endTimeBegin;
	/**
	 * 结束时间End
	 */
	private String endTimeEnd;
	/**
	 * 状态  JOD_STATUS
	 */
	private String jodStatus;
	
	/**
	 * 是否成功  is_success
	 */
	private java.lang.Integer isSuccess;
	
	/**
	 * 执行结果  result_msg
	 */
	private String resultMsg;
	


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setJodStatus(String value) {
		this.jodStatus = value;
	}
	
	public String getJodStatus() {
		return this.jodStatus;
	}
	public void setIsSuccess(java.lang.Integer value) {
		this.isSuccess = value;
	}
	
	public java.lang.Integer getIsSuccess() {
		return this.isSuccess;
	}
	public void setResultMsg(String value) {
		this.resultMsg = value;
	}
	
	public String getResultMsg() {
		return this.resultMsg;
	}

}

