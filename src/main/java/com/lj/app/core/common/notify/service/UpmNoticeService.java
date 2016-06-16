package com.lj.app.core.common.notify.service;

import java.util.List;

import com.lj.app.core.common.base.service.BaseService;
public interface UpmNoticeService<UpmNotice> extends BaseService {
	public List<UpmNotice> getUapNoticeMail(long queryCount,long eachNum);
}
