package com.lj.app.core.common.ibatis.dao;

import java.io.Serializable;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

/**
 * 
 *  MySqlMapClient扩展类
 *
 */
public class MySqlMapClientImpl extends SqlMapClientImpl implements Serializable {

  public MySqlMapClientImpl(SqlMapExecutorDelegate delegate) {
    super(delegate);
  }

}
