package com.lj.app.core.common.util;

public class AjaxResult{
	private String opResult;
	private String optFailureMsg;//操作失败消息

	public String getOpResult() {
		return opResult;
	}

	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}

	public String getOptFailureMsg() {
		return optFailureMsg;
	}

	public void setOptFailureMsg(String optFailureMsg) {
		this.optFailureMsg = optFailureMsg;
	}
	
}