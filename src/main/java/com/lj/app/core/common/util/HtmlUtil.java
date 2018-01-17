package com.lj.app.core.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HtmlUtil {
  private static final char[] realChar = { '<', '>', '&', '"' };
  private static final String[] htmlString = { "&lt;", "&gt;", "&amp;", "&quot;" };

  public static String list2Select(String id, List<String> list) {
    StringBuffer sb = new StringBuffer("<select id=\"").append(id == null ? "" : id).append("\">");

    for (Object s : list) {
      sb.append("<option value=\"").append(s).append("\">").append(s).append("</option>");
    }

    sb.append("</select>");

    return sb.toString();
  }

  public static String array2Select(String id, String[] array) {
    return list2Select(id, Arrays.asList(array));
  }

  public static String getButton(String type, String value, String attribute) {
    StringBuffer sb = new StringBuffer();

    sb.append("<input type=\"button\" value=\"").append(value == null ? "button" : value).append("\"")
        .append(attribute == null ? "" : attribute).append("/>");

    return sb.toString();
  }

  public static String mapList2Select(String selectId, List<Map<String, Object>> list) {
    StringBuffer sb = new StringBuffer("<select id=\"").append(selectId == null ? "" : selectId).append("\">");

    for (Map<String, Object> map : list) {
      sb.append("<option value=\"").append(map.get("value")).append("\">").append(map.get("label")).append("</option>");

    }

    sb.append("</select>");

    return sb.toString();
  }

  public static String htmlStringToRealChar(String toChangeStr) {
    for (int i = 0; i < htmlString.length; i++) {
      toChangeStr = toChangeStr.replace(htmlString[i], String.valueOf(realChar[i]));
    }
    return toChangeStr;
  }

  public static String realCharToHtmlString(String toChangeStr) {
    for (int i = 0; i < realChar.length; i++) {
      toChangeStr = toChangeStr.replace(String.valueOf(realChar[i]), htmlString[i]);
    }
    return toChangeStr;
  }
}
