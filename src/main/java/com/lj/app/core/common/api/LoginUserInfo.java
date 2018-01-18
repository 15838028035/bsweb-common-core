package com.lj.app.core.common.api;

/**
 * 
 * 登陆用户信息
 *
 */
public class LoginUserInfo {
  /**
   * ID
   */
  private java.lang.Integer id;
  /**
   * 登陆账号
   */
  private String loginNo;
  /**
   * 密码
   */
  private String pwd;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 地址
   */
  private String address;
  /**
   * 手机号码
   */
  private String mobile;
  /**
   * 组织机构描述
   */
  private String orgDesc;

  public java.lang.Integer getId() {
    return id;
  }

  public void setId(java.lang.Integer id) {
    this.id = id;
  }

  public String getLoginNo() {
    return loginNo;
  }

  public void setLoginNo(String loginNo) {
    this.loginNo = loginNo;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getOrgDesc() {
    return orgDesc;
  }

  public void setOrgDesc(String orgDesc) {
    this.orgDesc = orgDesc;
  }
}
