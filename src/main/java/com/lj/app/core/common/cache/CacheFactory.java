package com.lj.app.core.common.cache;

/**
 * 缓存工厂
 *
 */
public class CacheFactory {

	private static  MemoryCache memoryCache = new MemoryCache();
	
	public static  Cache getCache() {
		return memoryCache;
	}
}
