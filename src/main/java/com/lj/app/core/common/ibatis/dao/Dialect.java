package com.lj.app.core.common.ibatis.dao;

/**
 * 
 * 方言
 *
 */
public abstract class Dialect {
  public abstract boolean supportsLimit();

  public abstract boolean supportsLimitOffset();

  public String getLimitString(String sql, int offset, int limit) {
    return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
  }

  public abstract String getLimitString(String paramString1, int paramInt1, String paramString2, int paramInt2,
      String paramString3);
}