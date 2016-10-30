package com.lj.app.core.common.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lj.app.core.common.exception.CacheException;
import com.lj.app.core.common.util.StringUtil;

/**
 * 基于虚拟机内存的cache管理器
 */
public class MemoryCacheManager implements CacheManager {
	private final ConcurrentMap<String, Cache> caches;
	
	public MemoryCacheManager() {
		this.caches = new ConcurrentHashMap<String, Cache>();
	}
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if(StringUtil.isBlank(name)) {
			throw new IllegalArgumentException("Cache名称不能为空.");
		}
        Cache cache;

        cache = caches.get(name);
        if (cache == null) {
            cache = new MemoryCache<Object, Object>(new ConcurrentHashMap<Object, Object>());
            Cache existing = caches.putIfAbsent(name, cache);
            if (existing != null) {
                cache = existing;
            }
        }
        return cache;
	}

    public void destroy() throws CacheException {
        while (!caches.isEmpty()) {
            caches.clear();
        }
    }
}