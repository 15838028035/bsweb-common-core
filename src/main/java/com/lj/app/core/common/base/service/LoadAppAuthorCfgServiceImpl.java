package com.lj.app.core.common.base.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.config.authorsrc.AppAuthorConfigBean;
import com.lj.app.core.common.base.config.authorsrc.AppAuthorOption;
import com.lj.app.core.common.base.config.authorsrc.LoadAppAuthorConfig;



@Service("loadAppAuthorCfgService")
public class LoadAppAuthorCfgServiceImpl implements LoadAppAuthorCfgService {

	private static Log logger = LogFactory
			.getLog(LoadAppAuthorCfgServiceImpl.class);

	/**
	 * 获取应用的授权配置
	 * 
	 * @param appCode
	 *            应用编码
	 * @return
	 */
	public AppAuthorConfigBean getAppAuthorConfig(String appCode) {
		return LoadAppAuthorConfig.getAppAuthorConfig(appCode);
	}

	/**
	 * 获取授权实体的前台配置
	 * 
	 * @param appCode
	 *            应用编码
	 * @param opType
	 *            授权实体的类型
	 * @return
	 */
	public AppAuthorOption getAppAuthorOption(String appCode, String opType) {

		AppAuthorOption appAuthorOption = null;

		AppAuthorConfigBean appAuthorConfigBean = LoadAppAuthorConfig
				.getAppAuthorConfig(appCode);
		if (appAuthorConfigBean != null) {
			List<AppAuthorOption> optionList = appAuthorConfigBean.getOptions();
			for (AppAuthorOption _appAuthorOption : optionList) {
				if (_appAuthorOption.getType() != null
						&& _appAuthorOption.getType().equalsIgnoreCase(opType)) {
					appAuthorOption = _appAuthorOption;
					break;
				}
			}
		} else {
			logger.warn("Cann't find AppAuthorOption of [" + appCode + "-"
					+ opType + "]");
		}
		return appAuthorOption;
	}
}
