package com.lj.app.core.common.notify.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

@Service("upmNoticeService")
public class UpmNoticeServiceImpl<UpmNotice> extends BaseServiceImpl<UpmNotice> implements UpmNoticeService<UpmNotice>{

	@Override
	public List<UpmNotice> getUapNoticeMail(long queryCount, long eachNum) {
		Map<String,String> filterMap =new HashMap<String,String>();
		filterMap.put("typeId", "EMAIL");
		return this.queryForList("select",filterMap);
	}
}
