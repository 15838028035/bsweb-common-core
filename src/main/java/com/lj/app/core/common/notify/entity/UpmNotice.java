package com.lj.app.core.common.notify.entity;

import com.lj.app.core.common.base.entity.BaseModel;

public class UpmNotice extends BaseModel{
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 类别ID
	 */
	private String typeId;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * paramA
	 */
	private String paramA;
	
	/**
	 * 
	 */
	private String paramB;
	
	/**
	 * 发送开始时间
	 */
	private java.util.Date sendBeginDate;
	
	 /**
	 * 发送开始时间Begin
	 */
	private String  sendBeginDateBegin;
	/**
	 * 发送开始时间End
	 */
	private String sendBeginDateEnd;
	/**
	 * 发送结束日期
	 */
	private java.util.Date sendEndDate;
	
	 /**
	 * 发送结束日期Begin
	 */
	private String  sendEndDateBegin;
	/**
	 * 发送结束日期End
	 */
	private String sendEndDateEnd;
	/**
	 * 创建日期
	 */
	private java.util.Date createDateTime;
	
	 /**
	 * 创建日期Begin
	 */
	private String  createDateTimeBegin;
	/**
	 * 创建日期End
	 */
	private String createDateTimeEnd;
	/**
	 * 扩展code
	 */
	private String extCode;
	


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTypeId(String value) {
		this.typeId = value;
	}
	
	public String getTypeId() {
		return this.typeId;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setParamA(String value) {
		this.paramA = value;
	}
	
	public String getParamA() {
		return this.paramA;
	}
	public void setParamB(String value) {
		this.paramB = value;
	}
	
	public String getParamB() {
		return this.paramB;
	}
	public void setSendBeginDate(java.util.Date value) {
		this.sendBeginDate = value;
	}
	
	public java.util.Date getSendBeginDate() {
		return this.sendBeginDate;
	}
	public void setSendEndDate(java.util.Date value) {
		this.sendEndDate = value;
	}
	
	public java.util.Date getSendEndDate() {
		return this.sendEndDate;
	}
	public void setCreateDateTime(java.util.Date value) {
		this.createDateTime = value;
	}
	
	public java.util.Date getCreateDateTime() {
		return this.createDateTime;
	}
	public void setExtCode(String value) {
		this.extCode = value;
	}
	
	public String getExtCode() {
		return this.extCode;
	}

	public String getSendBeginDateBegin() {
		return sendBeginDateBegin;
	}

	public void setSendBeginDateBegin(String sendBeginDateBegin) {
		this.sendBeginDateBegin = sendBeginDateBegin;
	}

	public String getSendBeginDateEnd() {
		return sendBeginDateEnd;
	}

	public void setSendBeginDateEnd(String sendBeginDateEnd) {
		this.sendBeginDateEnd = sendBeginDateEnd;
	}

	public String getSendEndDateBegin() {
		return sendEndDateBegin;
	}

	public void setSendEndDateBegin(String sendEndDateBegin) {
		this.sendEndDateBegin = sendEndDateBegin;
	}

	public String getSendEndDateEnd() {
		return sendEndDateEnd;
	}

	public void setSendEndDateEnd(String sendEndDateEnd) {
		this.sendEndDateEnd = sendEndDateEnd;
	}

	public String getCreateDateTimeBegin() {
		return createDateTimeBegin;
	}

	public void setCreateDateTimeBegin(String createDateTimeBegin) {
		this.createDateTimeBegin = createDateTimeBegin;
	}

	public String getCreateDateTimeEnd() {
		return createDateTimeEnd;
	}

	public void setCreateDateTimeEnd(String createDateTimeEnd) {
		this.createDateTimeEnd = createDateTimeEnd;
	}
	
}

