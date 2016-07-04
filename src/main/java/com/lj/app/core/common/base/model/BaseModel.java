package com.lj.app.core.common.base.model;

import java.io.Serializable;

import com.lj.app.core.common.util.StringUtil;

@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	/**
	 * 创建人
	 */
	private int createBy;
	/**
	 * 创建日期
	 */
	private String createDate;
	/**
	 * 更新人
	 */
	private int updateBy;
	/**
	 * 更新日期
	 */
	private String updateDate;

	/**
	 * 是否有效
	 */
	private String enableFlag = "T";

	/**
	 * 加载状态
	 */
	private String lockStatus = "0";

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 排序字段
	 */
	private String sidx;

	/**
	 * 排序方式
	 */
	private String sord;

	/**
	 * 从写toString方法
	 */
	public String toString() {
		return StringUtil.props(this);
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
