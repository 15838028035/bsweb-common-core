package com.lj.app.core.common.ibatis.dao;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import java.sql.Connection;
import java.sql.SQLException;

public class LimitSqlExecutor extends SqlExecutor {
  private Dialect dialect;
  private boolean enableLimit = true;

  public Dialect getDialect() {
    return this.dialect;
  }

  public void setDialect(Dialect dialect) {
    this.dialect = dialect;
  }

  public boolean isEnableLimit() {
    return this.enableLimit;
  }

  public void setEnableLimit(boolean enableLimit) {
    this.enableLimit = enableLimit;
  }

  public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters,
      int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
    if ((supportsLimit()) && ((skipResults != 0) || (maxResults != -999999))) {
      sql = sql.trim();
      if (this.dialect.supportsLimitOffset()) {
        sql = this.dialect.getLimitString(sql, skipResults, maxResults);
        skipResults = 0;
      } else {
        sql = this.dialect.getLimitString(sql, 0, maxResults);
      }
      maxResults = -999999;
    }
    super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
  }

  public boolean supportsLimit() {
    if ((this.enableLimit) && (this.dialect != null)) {
      return this.dialect.supportsLimit();
    }
    return false;
  }
}