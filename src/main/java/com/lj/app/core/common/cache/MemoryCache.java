package com.lj.app.core.common.cache;

import java.io.Serializable;
import java.util.Map;

import com.lj.app.core.common.exception.CacheException;
import com.lj.app.core.common.util.Assert;

/**
 * 基于内存管理cache
 */
public class MemoryCache<K, V> implements Cache<K, V>, Serializable {
	/**
	 * map cache
	 */
	private final Map<K, V> map;
	/**
	 * 通过Map实现类构造MemoryCache
	 * @param backingMap
	 */
	public MemoryCache(Map<K, V> backingMap) {
		Assert.notNull(backingMap);
		this.map = backingMap;
	}
	
	public V get(K key) throws CacheException {
		return map.get(key);
	}

	public V put(K key, V value) throws CacheException {
		return map.put(key, value);
	}

	public V remove(K key) throws CacheException {
		return map.remove(key);
	}

	public void clear() throws CacheException {
		map.clear();
	}
}