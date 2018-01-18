package com.lj.app.core.common.task.service;

import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.service.BaseServiceImpl;

/**
 * 
 * job服务类
 *
 * @param <UpmJob> job对象
 */
@Service("upmJobService")
public class UpmJobServiceImpl<UpmJob> extends BaseServiceImpl<UpmJob> implements UpmJobService<UpmJob> {

}
