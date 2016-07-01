package com.lj.app.core.common.base.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseModel {

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
		return props(this);
	}

	/**
	 * 显示实体类的属性和方法
	 * @param o
	 * @return
	 */
	public String props(Object o) {
		String toString = "";
		Class cls = o.getClass();
		String className = "";
		className = cls.getName();
		toString = "**** " + className + " attribute list begin **\r\n";
		while (cls != null) {
			Method[] mth = cls.getDeclaredMethods();
			Field[] fd = cls.getDeclaredFields();
			try {
				for (int i = 0; i < mth.length; i++) {
					String str = mth[i].getName();
					if (str.startsWith("g")) {
						for (int k = 0; k < fd.length; k++) {
							String st = fd[k].getName();
							if (str.toLowerCase().indexOf(st.toLowerCase()) > 0) {
								toString = toString + st + "==="
										+ mth[i].invoke(o, null) + "\r\n";
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			cls = cls.getSuperclass();
		}
		toString = toString + "** " + className
				+ " attribute list end *****\r\n";
		return toString;
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
