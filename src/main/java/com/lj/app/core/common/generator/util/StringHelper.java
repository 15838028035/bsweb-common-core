package com.lj.app.core.common.generator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lj.app.core.common.generator.GeneratorConstants;
import com.lj.app.core.common.generator.GeneratorProperties;

public class StringHelper {
  private static final Random RANDOM = new Random();

  /**
   * 去除空格
   * 
   * @param str
   * @return
   */
  public static String trimBlank(String str) {
    return str == null ? "" : str.trim();
  }

  public static boolean isBlank(String str) {
    return str == null || str.trim().length() == 0;
  }

  public static boolean isNotBlank(String str) {
    return !isBlank(str);
  }

  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  public static String emptyIf(String value, String defaultValue) {
    if ((value == null) || ("".equals(value))) {
      return defaultValue;
    }
    return value;
  }

  public static String defaultString(Object value) {
    if (value == null) {
      return "";
    }
    return value.toString();
  }

  public static String defaultIfEmpty(Object value, String defaultValue) {
    if (value == null || "".equals(value)) {
      return defaultValue;
    }
    return value.toString();
  }

  public static String makeAllWordFirstLetterUpperCase(String sqlName) {
    if (sqlName == null) {
      return null;
    }

    String[] strs = sqlName.toLowerCase().split("_");
    String result = "";
    String preStr = "";
    for (int i = 0; i < strs.length; i++) {
      if (preStr.length() == 1)
        result = result + strs[i];
      else {
        result = result + capitalize(strs[i]);
      }
      preStr = strs[i];
    }
    return result;
  }

  public static String replace(String inString, String oldPattern, String newPattern) {
    if (inString == null) {
      return null;
    }
    if ((oldPattern == null) || (newPattern == null)) {
      return inString;
    }

    StringBuilder sbuf = new StringBuilder();

    int pos = 0;
    int index = inString.indexOf(oldPattern);

    int patLen = oldPattern.length();
    while (index >= 0) {
      sbuf.append(inString.substring(pos, index));
      sbuf.append(newPattern);
      pos = index + patLen;
      index = inString.indexOf(oldPattern, pos);
    }
    sbuf.append(inString.substring(pos));

    return sbuf.toString();
  }

  public static String capitalize(String str) {
    return changeFirstCharacterCase(str, true);
  }

  public static String uncapitalize(String str) {
    return changeFirstCharacterCase(str, false);
  }

  private static String changeFirstCharacterCase(String str, boolean capitalize) {
    if ((str == null) || (str.length() == 0)) {
      return str;
    }
    StringBuilder buf = new StringBuilder(str.length());
    if (capitalize) {
      buf.append(Character.toUpperCase(str.charAt(0)));
    } else {
      buf.append(Character.toLowerCase(str.charAt(0)));
    }
    buf.append(str.substring(1));
    return buf.toString();
  }

  public static String randomNumeric(int count) {
    return random(count, false, true);
  }

  public static String random(int count, boolean letters, boolean numbers) {
    return random(count, 0, 0, letters, numbers);
  }

  public static String random(int count, int start, int end, boolean letters, boolean numbers) {
    return random(count, start, end, letters, numbers, null, RANDOM);
  }

  public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars,
      Random random) {
    if (count == 0)
      return "";
    if (count < 0) {
      throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
    }

    if ((start == 0) && (end == 0)) {
      end = 123;
      start = 32;
      if ((!letters) && (!numbers)) {
        start = 0;
        end = 2147483647;
      }
    }

    char[] buffer = new char[count];
    int gap = end - start;

    while (count-- != 0) {
      char ch;
      if (chars == null)
        ch = (char) (random.nextInt(gap) + start);
      else {
        ch = chars[(random.nextInt(gap) + start)];
      }
      if (((letters) && (Character.isLetter(ch))) || ((numbers) && (Character.isDigit(ch)))
          || ((!letters) && (!numbers))) {
        if ((ch >= 56320) && (ch <= 57343)) {
          if (count == 0) {
            count++;
          } else {
            buffer[count] = ch;
            count--;
            buffer[count] = (char) (55296 + random.nextInt(128));
          }
        } else if ((ch >= 55296) && (ch <= 56191)) {
          if (count == 0) {
            count++;
          } else {
            buffer[count] = (char) (56320 + random.nextInt(128));
            count--;
            buffer[count] = ch;
          }
        } else if ((ch >= 56192) && (ch <= 56319)) {
          count++;
        } else
          buffer[count] = ch;
      } else {
        count++;
      }
    }
    return new String(buffer);
  }

  public static String toUnderscoreName(String name) {
    if (name == null)
      return null;

    String filteredName = name;
    if ((filteredName.indexOf("_") >= 0) && (filteredName.equals(filteredName.toUpperCase()))) {
      filteredName = filteredName.toLowerCase();
    }
    if ((filteredName.indexOf("_") == -1) && (filteredName.equals(filteredName.toUpperCase()))) {
      filteredName = filteredName.toLowerCase();
    }

    StringBuilder result = new StringBuilder();
    if ((filteredName != null) && (filteredName.length() > 0)) {
      result.append(filteredName.substring(0, 1).toLowerCase());
      for (int i = 1; i < filteredName.length(); i++) {
        String preChart = filteredName.substring(i - 1, i);
        String c = filteredName.substring(i, i + 1);
        if (c.equals("_")) {
          result.append("_");
        } else if (preChart.equals("_")) {
          result.append(c.toLowerCase());
        } else if (c.matches("\\d")) {
          result.append(c);
        } else if (c.equals(c.toUpperCase())) {
          result.append("_");
          result.append(c.toLowerCase());
        } else {
          result.append(c);
        }
      }
    }
    return result.toString();
  }

  public static String getJavaClassSimpleName(String clazz) {
    if (clazz == null)
      return null;
    if (clazz.lastIndexOf(".") >= 0) {
      return clazz.substring(clazz.lastIndexOf(".") + 1);
    }
    return clazz;
  }

  /**
   * 将一个数据库名称转换为 Java 变量名称,如 user_info => userInfo
   * 
   * @param sqlName
   * @param singularize
   * @return
   */
  public static String toJavaClassName(String sqlName) {
    String processedSqlName = removeTableSqlNamePrefix(sqlName);
    return makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(processedSqlName));
  }

  public static String removeTableSqlNamePrefix(String sqlName) {
    String[] prefixs = GeneratorProperties.getStringArray(GeneratorConstants.TABLE_REMOVE_PREFIXES);
    for (String prefix : prefixs) {
      String removedPrefixSqlName = StringHelper.removePrefix(sqlName, prefix, true);
      if (removedPrefixSqlName!=null && !removedPrefixSqlName.equals(sqlName)) {
        return removedPrefixSqlName;
      }
    }
    return sqlName;
  }

  public static String removePrefix(String str, String prefix) {
    return removePrefix(str, prefix, false);
  }

  public static String removePrefix(String str, String prefix, boolean ignoreCase) {
    if (str == null)
      return null;
    if (prefix == null)
      return str;
    if (ignoreCase) {
      if (str.toLowerCase().startsWith(prefix.toLowerCase())) {
        return str.substring(prefix.length());
      }
    } else {
      if (str.startsWith(prefix)) {
        return str.substring(prefix.length());
      }
    }
    return str;
  }

  public static String removeCrlf(String str) {
    if (str == null)
      return null;
    return StringHelper.join(StringHelper.tokenizeToStringArray(str, "\t\n\r\f"), " ");
  }

  public static String[] tokenizeToStringArray(String str, String seperators) {
    if (str == null)
      return new String[0];
    StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
    List result = new ArrayList();
    while (tokenlizer.hasMoreElements()) {
      Object s = tokenlizer.nextElement();
      result.add(s);
    }
    return (String[]) result.toArray(new String[result.size()]);
  }

  public static String join(List list, String seperator) {
    return join(list.toArray(new Object[0]), seperator);
  }

  public static String join(Object[] array, String seperator) {
    if (array == null)
      return null;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      result.append(array[i]);
      if (i != array.length - 1) {
        result.append(seperator);
      }
    }
    return result.toString();
  }

  public static String getExtension(String str) {
    if (str == null)
      return null;
    int i = str.lastIndexOf('.');
    if (i >= 0) {
      return str.substring(i + 1);
    }
    return null;
  }

  public static int indexOfByRegex(String input, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(input);
    if (m.find()) {
      return m.start();
    }
    return -1;
  }

  /**
   * Test whether the given string matches the given substring at the given index.
   * 
   * @param str
   *          the original string (or StringBuilder)
   * @param index
   *          the index in the original string to start matching against
   * @param substring
   *          the substring to match at the given index
   */
  public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
    for (int j = 0; j < substring.length(); j++) {
      int i = index + j;
      if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
        return false;
      }
    }
    return true;
  }

}