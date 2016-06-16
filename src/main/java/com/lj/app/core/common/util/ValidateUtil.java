package com.lj.app.core.common.util;

import org.apache.commons.lang3.StringUtils;

public class ValidateUtil {

	public static boolean isNotEmpty(String  str) {
		return StringUtils.isNotBlank(str);
	}

	public static boolean isEmpty(String str) {
		return StringUtils.isBlank(str);
	}
	

}
