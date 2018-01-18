package com.lj.app.core.common.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * 日期格式
 *
 */
public class DateJsonFormat implements JsonValueProcessor {

  /**
   * 处理数组值
   */
  public Object processArrayValue(Object value, JsonConfig arg1) {
    String[] obj = {};
    if (value instanceof Date[]) {
      Date[] dates = (Date[]) value;
      obj = new String[dates.length];
      for (int i = 0; i < dates.length; i++) {
        obj[i] = DateUtil.formatDate((Date) value, DateUtil.DATE_FOMRAT_yyyy_MM_dd_hh_MMss);
      }
    }
    return obj;
  }

  /**
   * 处理对象值
   */
  public Object processObjectValue(String key, Object value, JsonConfig arg2) {
    if (value == null) {
      return "";
    }

    if (value instanceof Date) {
      return DateUtil.formatDate((Date) value, DateUtil.DATE_FOMRAT_yyyy_MM_dd_hh_MMss);
    }
    return value;
  }
}
