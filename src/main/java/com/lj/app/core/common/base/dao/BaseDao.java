package com.lj.app.core.common.base.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lj.app.core.common.base.entity.BaseEntity;

public interface BaseDao<T> {

  public SqlMapClient getSqlMapClientProfile() throws Exception;

  public SqlMapClientTemplate getSqlMapClientTemplateProfile() throws Exception;

  public void insertObject(String sqlid, Object obj) throws Exception;

  public int insertObjectReturnKey(String sqlid, Object obj) throws Exception;

  public int insertObjectReturnKey(Object obj) throws Exception;

  public Object insertObjectReturn(String sqlid, Object obj) throws Exception;

  public Object insertObjectReturn(Object obj) throws Exception;

  public void updateObject(String sqlId, Object obj) throws Exception;

  public void deleteObject(String sqlId, Object obj);

  public BaseEntity findObject(String sqlId, Object obj) throws Exception;

  public Object queryForObject(String sqlId, Object obj);

  public BaseEntity getInfoByKey(String sqlId, Object obj);

  public List<BaseEntity> findBaseModeList(String sqlId, Object obj) throws Exception;

  public List<BaseEntity> findBaseModePageList(String sqlId, Object obj) throws Exception;

  public List queryForList(String statementName) throws DataAccessException;

  public List queryForList(String statementName, Object parameterObject);

  public List queryForList(String statementName, int skipResults, int maxResults) throws DataAccessException;

  public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults)
      throws DataAccessException;

  public int countObject(String sqlId, Object obj) throws Exception;

  /**
   * 批量添加 Title: insertBatch
   * 
   * @author zhangtq
   *         <p>
   *         Description:
   *         </p>
   * @param dataList
   *          dataList
   * @return int
   * @throws SQLException
   *           int
   */
  public int insertBatch(List dataList) throws SQLException;

  public int insertBatch(String sqlId, List dataList) throws SQLException;

}
