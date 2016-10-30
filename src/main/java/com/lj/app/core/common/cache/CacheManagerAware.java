package com.lj.app.core.common.cache;

/**
 * 该接口的实现类，需要设置cache管理器
 */
public interface CacheManagerAware {
	/**
	 * 设置cache管理器
	 * @param cacheManager
	 */
	public void setCacheManager(CacheManager cacheManager);
}
