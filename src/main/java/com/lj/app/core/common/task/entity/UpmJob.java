package com.lj.app.core.common.task.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
*UpmJob111
*/
public class UpmJob extends BaseEntity{
	
	/**
	 * ID  id
	 */
	private java.lang.Integer id;
	
	/**
	 * 定时任务名称  job_name
	 */
	private String jobName;
	
	/**
	 * job执行类  job_class
	 */
	private String jobClass;
	
	/**
	 * 执行表达式  job_Frequency
	 */
	private String jobFrequency;
	
	/**
	 * 星期  job_weekday
	 */
	private java.lang.Integer jobWeekday;
	
	/**
	 * 月天  job_monthday
	 */
	private java.lang.Integer jobMonthday;
	
	/**
	 * 小时  job_hour
	 */
	private java.lang.Integer jobHour;
	
	/**
	 * 任务描述  JOB_DESC
	 */
	private String jobDesc;
	
	/**
	 * 调度日志ID  SCHE_DETAIL_ID
	 */
	private java.lang.Integer scheDetailId;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobFrequency() {
		return jobFrequency;
	}

	public void setJobFrequency(String jobFrequency) {
		this.jobFrequency = jobFrequency;
	}

	public java.lang.Integer getJobWeekday() {
		return jobWeekday;
	}

	public void setJobWeekday(java.lang.Integer jobWeekday) {
		this.jobWeekday = jobWeekday;
	}

	public java.lang.Integer getJobMonthday() {
		return jobMonthday;
	}

	public void setJobMonthday(java.lang.Integer jobMonthday) {
		this.jobMonthday = jobMonthday;
	}

	public java.lang.Integer getJobHour() {
		return jobHour;
	}

	public void setJobHour(java.lang.Integer jobHour) {
		this.jobHour = jobHour;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public java.lang.Integer getScheDetailId() {
		return scheDetailId;
	}

	public void setScheDetailId(java.lang.Integer scheDetailId) {
		this.scheDetailId = scheDetailId;
	}
}

