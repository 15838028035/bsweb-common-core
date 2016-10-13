package com.lj.app.core.common.base.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.properties.PropertiesReader;
import com.lj.app.core.common.util.SpringContextHolder;

@Service("upmConfigurationService")
public class UpmConfigurationServiceImpl<UpmConfiguration> extends BaseServiceImpl<UpmConfiguration> implements UpmConfigurationService<UpmConfiguration>{

	public  void reloadConfigPro() {
		UpmConfigurationService<UpmConfiguration> upmConfigurationService = SpringContextHolder.getBean("upmConfigurationService");
		// 从数据库中获取配置项数据
		List<com.lj.app.core.common.base.entity.UpmConfiguration> list = upmConfigurationService.queryForList(new HashMap());
		for (com.lj.app.core.common.base.entity.UpmConfiguration obj : list){
			obj = (com.lj.app.core.common.base.entity.UpmConfiguration)obj;
			PropertiesReader.getProperties().setProperty(obj.getCfgKey(), null==obj.getCfgValue()?"":obj.getCfgValue());
		}
	}
	
}
