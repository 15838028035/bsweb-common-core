package com.lj.app.core.common.notify.sysmsg;

/**
 * ISysmsgSender
*/
public interface ISysmsgSender {

	boolean sendSysmsg(String title, String content, Long mainAcctId);
}