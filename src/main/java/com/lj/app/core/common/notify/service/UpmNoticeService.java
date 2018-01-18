package com.lj.app.core.common.notify.service;

import java.util.List;

import com.lj.app.core.common.base.service.BaseService;

/**
 * 通知服务类
 *
 * @param <UpmNotice>
 *          通知对象
 */
public interface UpmNoticeService<UpmNotice> extends BaseService {
  public List<UpmNotice> getUapNoticeMail(long queryCount, long eachNum);
}
