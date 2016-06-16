package com.lj.app.core.common.util;

import java.util.HashMap;

public class CacheUtil {
	public static HashMap<String, Object> cache = new HashMap<String ,Object>();
	
	public static void store(String key ,Object obj){
		cache.put(key, obj);
	}
	
	public static Object find(String key){
		return cache.get(key);
	}
	
	public static void removeCache(String key){
		cache.remove(key);
	}
	
	public static boolean hasStore(String key){
		return cache.get(key) != null ;
	}
	
	public static int getCacheSize(){
		return cache.size();
	}
	
	public static void clealAll() {
		cache.clear();
	}
	
}
