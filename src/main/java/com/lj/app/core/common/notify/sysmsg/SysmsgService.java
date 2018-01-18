package com.lj.app.core.common.notify.sysmsg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.properties.PropertiesUtil;

/**
 * 系统消息服务
 *
 */
public class SysmsgService {

  private static Log logger = LogFactory.getLog(SysmsgService.class);

  /**
   * 发送短信
   * 
   * @param title
   *          标题
   * @param content
   *          内容
   * @param mainAcctId
   *          帐号Id
   * @return boolean 是否成功
   */
  public static boolean sendSmsInfo(String title, String content, Long mainAcctId) {
    boolean result = false;
    if (mainAcctId != null) {
      String systemName = PropertiesUtil.getProperty("systemName");
      content = systemName + ":" + content;
      ISysmsgSender sysmsgSender = SysmsgSenderFactory.createSysmsgSenderImpl();

      logger.warn("**********start invoke sms service:");
      result = sysmsgSender.sendSysmsg(title, content, mainAcctId);
      logger.warn("**********end invoke sms service:");
    }

    return result;
  }

}