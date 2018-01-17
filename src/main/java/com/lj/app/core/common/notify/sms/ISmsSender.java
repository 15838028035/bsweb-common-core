package com.lj.app.core.common.notify.sms;

import java.sql.Timestamp;

/**
 * 
 * ISmsSender
 *
 */
public interface ISmsSender {
  /**
   * 发送短信
   * 
   * @param mobile
   *          手机号码
   * @param content
   *          短信内容
   * @param beginTime
   *          开始时间
   * @param endTime
   *          结束时间
   * @return 是否成功
   */
  public boolean sendSms(String mobile, String content, Timestamp beginTime, Timestamp endTime);
}