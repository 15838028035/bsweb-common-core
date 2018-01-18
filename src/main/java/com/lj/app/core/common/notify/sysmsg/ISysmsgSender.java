package com.lj.app.core.common.notify.sysmsg;

/**
 * ISysmsgSender
 */
public interface ISysmsgSender {

  /**
   * 发送系统消息
   * 
   * @param title
   *          标题
   * @param content
   *          内容
   * @param mainAcctId
   *          帐号Id
   * @return boolean 是否成功
   */
  public boolean sendSysmsg(String title, String content, Long mainAcctId);
}