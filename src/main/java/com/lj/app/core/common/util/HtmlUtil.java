package com.lj.app.core.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * Html工具类
 *
 */
public class HtmlUtil {
  public static final char [] REALCHAR = { '<', '>', '&', '"' };
  public static final String[] HTMLSTRING = { "&lt;", "&gt;", "&amp;", "&quot;" };

  /**
   * 列表转化下拉列表
   * @param id id
   * @param list 列表数据
   * @return 列表转化下拉列表
   */
  public static String list2Select(String id, List<String> list) {
    StringBuilder sb = new StringBuilder("<select id=\"").append(id == null ? "" : id).append("\">");

    for (Object s : list) {
      sb.append("<option value=\"").append(s).append("\">").append(s).append("</option>");
    }

    sb.append("</select>");

    return sb.toString();
  }

  public static String array2Select(String id, String[] array) {
    return list2Select(id, Arrays.asList(array));
  }

  /**
   * 获得按钮
   * @param type 类型
   * @param value 值
   * @param attribute 属性
   * @return 按钮
   */
  public static String getButton(String type, String value, String attribute) {
    StringBuilder sb = new StringBuilder();

    sb.append("<input type=\"button\" value=\"").append(value == null ? "button" : value).append("\"")
        .append(attribute == null ? "" : attribute).append("/>");

    return sb.toString();
  }

  /**
   * 下来列表
   * @param selectId selectedId
   * @param list 列表
   * @return 下来列表
   */
  public static String mapList2Select(String selectId, List<Map<String, Object>> list) {
    StringBuilder sb = new StringBuilder("<select id=\"").append(selectId == null ? "" : selectId).append("\">");

    for (Map<String, Object> map : list) {
      sb.append("<option value=\"").append(map.get("value")).append("\">").append(map.get("label")).append("</option>");

    }

    sb.append("</select>");

    return sb.toString();
  }

  /**
   * HTML字符替换
   * @param toChangeStr HTML字符替换
   * @return HTML字符替换
   */
  public static String htmlStringToRealChar(String toChangeStr) {
    for (int i = 0; i < HTMLSTRING.length; i++) {
      toChangeStr = toChangeStr.replace(HTMLSTRING[i], String.valueOf(REALCHAR[i]));
    }
    return toChangeStr;
  }

  /**
   * HTML字符替换
   * @param toChangeStr HTML字符替换
   * @return HTML字符替换
   */
  public static String realCharToHtmlString(String toChangeStr) {
    for (int i = 0; i < REALCHAR.length; i++) {
      toChangeStr = toChangeStr.replace(String.valueOf(REALCHAR[i]), HTMLSTRING[i]);
    }
    return toChangeStr;
  }
}
