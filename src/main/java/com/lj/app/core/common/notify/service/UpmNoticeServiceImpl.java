package com.lj.app.core.common.notify.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

@Service("upmNoticeService")
public class UpmNoticeServiceImpl<UpmNotice> extends BaseServiceImpl<UpmNotice> implements UpmNoticeService<UpmNotice>{

	@Override
	public List<UpmNotice> getUapNoticeMail(long queryCount, long eachNum) {
		//TODO:FIXME
		return this.queryForList("select",null);
	}
}
