package com.lj.app.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonFormat implements JsonValueProcessor {
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public Object processArrayValue(Object value, JsonConfig arg1) {
		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(dateFormat).format((Date) value);
			return str;
		}
		return value;
	}
}
