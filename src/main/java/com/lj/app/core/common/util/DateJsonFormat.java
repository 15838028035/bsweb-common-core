package com.lj.app.core.common.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonFormat implements JsonValueProcessor {
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public Object processArrayValue(Object value, JsonConfig arg1) {
		String[] obj = {};
		if (value instanceof Date[]) {
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = DateUtil.formatDate((Date)value, dateFormat);
			}
		}
		return obj;
	}

	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if (value instanceof Date) {
			return DateUtil.formatDate((Date)value, dateFormat);
		}
		return value;
	}
}
