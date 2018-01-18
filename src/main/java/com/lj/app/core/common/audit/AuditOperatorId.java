package com.lj.app.core.common.audit;

/**
 * 审计日志操作ID
 * 
 */
public class AuditOperatorId {
  /**
   * 登录和退出相关类
   * 
   */
  public static class Login {
    public final static String LOGIN = "1-UAP-10000";
    public final static String LOGOUT = "1-UAP-10027";
    public final static String LOCK = "1-UAP-10058";
  }

}
