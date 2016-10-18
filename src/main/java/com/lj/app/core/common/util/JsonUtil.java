package com.lj.app.core.common.util;

import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * 将对象转换为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		JSONObject jsonObj = JSONObject.fromObject(object);
		return jsonObj.toString();
	}
	
	/**
	 * 根据指定类型解析json字符串，并返回该类型的对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		JSONObject jsonObject =JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObject, clazz);
	}
}
