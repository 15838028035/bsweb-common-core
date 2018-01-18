package com.lj.app.core.common.base.entity;

import com.lj.app.core.common.base.entity.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 系统日志111
 */
public class UpmSysLog extends BaseEntity {

  /**
   * ID id
   */
  private java.lang.Integer id;

  /**
   * 用户名 user_name
   */
  private String userName;

  /**
   * 用户操作 operation
   */
  private String operation;

  /**
   * 请求方法 method
   */
  private String method;

  /**
   * 请求参数 params
   */
  private String params;

  /**
   * IP地址 ip
   */
  private String ip;

  /**
   * 创建时间 reate_date
   */
  private java.util.Date reateDate;

  /**
   * 创建时间Begin
   */
  private String reateDateBegin;
  /**
   * 创建时间End
   */
  private String reateDateEnd;

  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  public java.lang.Integer getId() {
    return this.id;
  }

  public void setUserName(String value) {
    this.userName = value;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setOperation(String value) {
    this.operation = value;
  }

  public String getOperation() {
    return this.operation;
  }

  public void setMethod(String value) {
    this.method = value;
  }

  public String getMethod() {
    return this.method;
  }

  public void setParams(String value) {
    this.params = value;
  }

  public String getParams() {
    return this.params;
  }

  public void setIp(String value) {
    this.ip = value;
  }

  public String getIp() {
    return this.ip;
  }

  public void setReateDate(java.util.Date value) {
    this.reateDate = value;
  }

  public java.util.Date getReateDate() {
    return this.reateDate;
  }

  public String toString() {
    return new ToStringBuilder(this).append("Id", getId()).append("UserName", getUserName())
        .append("Operation", getOperation()).append("Method", getMethod()).append("Params", getParams())
        .append("Ip", getIp()).append("ReateDate", getReateDate()).toString();
  }

  public int hashCode() {
    return new HashCodeBuilder().append(getId()).append(getUserName()).append(getOperation()).append(getMethod())
        .append(getParams()).append(getIp()).append(getReateDate()).toHashCode();
  }

  public boolean equals(Object obj) {
    if (obj instanceof UpmSysLog == false)
      return false;
    if (this == obj)
      return true;
    UpmSysLog other = (UpmSysLog) obj;
    return new EqualsBuilder().append(getId(), other.getId()).append(getUserName(), other.getUserName())
        .append(getOperation(), other.getOperation()).append(getMethod(), other.getMethod())
        .append(getParams(), other.getParams()).append(getIp(), other.getIp())
        .append(getReateDate(), other.getReateDate()).isEquals();
  }
}
