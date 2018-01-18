package com.lj.app.core.common.generator.util;

import java.util.HashMap;

public class DatabaseDataTypesUtils {
  private static final IntStringMap _preferredJavaTypeForSqlType = new IntStringMap();

  public static boolean isFloatNumber(int sqlType, int size, int decimalDigits) {
    return isFloatNumber(getPreferredJavaType(sqlType, size, decimalDigits));
  }

  public static boolean isFloatNumber(String javaType) {
    if (javaType == null) {
      return false;
    }
    return (javaType.endsWith("Float")) || (javaType.endsWith("Double")) || (javaType.endsWith("BigDecimal"));
  }

  public static boolean isIntegerNumber(int sqlType, int size, int decimalDigits) {
    return isIntegerNumber(getPreferredJavaType(sqlType, size, decimalDigits));
  }

  public static boolean isIntegerNumber(String javaType) {
    return (javaType.endsWith("Long")) || (javaType.endsWith("Integer")) || (javaType.endsWith("Short"))
        || (javaType.endsWith("Byte"));
  }

  public static boolean isDate(int sqlType, int size, int decimalDigits) {
    return isDate(getPreferredJavaType(sqlType, size, decimalDigits));

  }

  public static boolean isDate(String javaType) {
    if (javaType == null) {
      return false;
    }
    return (javaType.endsWith("Date")) || (javaType.endsWith("Timestamp")) || (javaType.endsWith("Time"));
  }

  public static boolean isString(int sqlType, int size, int decimalDigits) {
    return isString(getPreferredJavaType(sqlType, size, decimalDigits));
  }

  public static boolean isString(String javaType) {
    if (javaType == null) {
      return false;
    }
    return javaType.endsWith("String");
  }

  public static String getPreferredJavaType(int sqlType, int size, int decimalDigits) {
    if (((sqlType == 3) || (sqlType == 2)) && (decimalDigits == 0)) {
      if (size == 1) {
        return "java.lang.Boolean";
      }
      if (size < 3)
        return "java.lang.Byte";
      if (size < 5)
        return "java.lang.Short";
      if (size < 10)
        return "java.lang.Integer";
      if (size < 19) {
        return "java.lang.Long";
      }
      return "java.math.BigDecimal";
    }

    String result = _preferredJavaTypeForSqlType.getString(sqlType);
    if (result == null) {
      result = "java.lang.Object";
    }
    return result;
  }

  static {
    _preferredJavaTypeForSqlType.put(-6, "java.lang.Byte");
    _preferredJavaTypeForSqlType.put(5, "java.lang.Short");
    _preferredJavaTypeForSqlType.put(4, "java.lang.Integer");
    _preferredJavaTypeForSqlType.put(-5, "java.lang.Long");
    _preferredJavaTypeForSqlType.put(7, "java.lang.Float");
    _preferredJavaTypeForSqlType.put(6, "java.lang.Double");
    _preferredJavaTypeForSqlType.put(8, "java.lang.Double");
    _preferredJavaTypeForSqlType.put(3, "java.math.BigDecimal");
    _preferredJavaTypeForSqlType.put(2, "java.math.BigDecimal");
    _preferredJavaTypeForSqlType.put(-7, "java.lang.Boolean");
    _preferredJavaTypeForSqlType.put(16, "java.lang.Boolean");
    _preferredJavaTypeForSqlType.put(1, "String");
    _preferredJavaTypeForSqlType.put(12, "String");

    _preferredJavaTypeForSqlType.put(-1, "String");
    _preferredJavaTypeForSqlType.put(-2, "byte[]");
    _preferredJavaTypeForSqlType.put(-3, "byte[]");
    _preferredJavaTypeForSqlType.put(-4, "byte[]");
    _preferredJavaTypeForSqlType.put(91, "java.sql.Date");
    _preferredJavaTypeForSqlType.put(92, "java.sql.Time");
    _preferredJavaTypeForSqlType.put(93, "java.sql.Timestamp");
    _preferredJavaTypeForSqlType.put(2005, "java.sql.Clob");
    _preferredJavaTypeForSqlType.put(2004, "java.sql.Blob");
    _preferredJavaTypeForSqlType.put(2003, "java.sql.Array");
    _preferredJavaTypeForSqlType.put(2006, "java.sql.Ref");
    _preferredJavaTypeForSqlType.put(2002, "java.lang.Object");
    _preferredJavaTypeForSqlType.put(2000, "java.lang.Object");
  }

  private static class IntStringMap extends HashMap {

    public String getString(int i) {
      return (String) get(new Integer(i));
    }

    public String[] getStrings(int i) {
      return (String[]) (String[]) get(new Integer(i));
    }

    public void put(int i, String s) {
      put(new Integer(i), s);
    }

    public void put(int i, String[] sa) {
      put(new Integer(i), sa);
    }

  }
}