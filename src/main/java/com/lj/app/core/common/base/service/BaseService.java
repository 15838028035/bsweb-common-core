package com.lj.app.core.common.base.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lj.app.core.common.base.entity.BaseEntity;
import com.lj.app.core.common.pagination.Page;

/**
 * 
 * 基础服务
 *
 * @param <T> 对象
 */
@SuppressWarnings("all")
public interface BaseService<T> {

  public void insertObject(Object obj) throws Exception;

  public void insertObject(String sqlId, Object obj) throws Exception;

  public int insertObjectReturnKey(String sqlid, Object obj) throws Exception;

  public int insertObjectReturnKey(Object obj) throws Exception;

  public Object insertObjectReturn(String sqlid, Object obj) throws Exception;

  public Object insertObjectReturn(Object obj) throws Exception;

  public void updateObject(Object obj) throws Exception;

  public void updateObject(String sqlId, Object obj) throws Exception;

  public void saveOrUpdate(Object obj, Object id) throws Exception;

  public void saveOrUpdate(String sqlId, Object obj, Object id) throws Exception;

  public BaseEntity findObject(String sqlId, Object obj) throws Exception;

  public BaseEntity getInfoByKey(String sqlId, Object obj);

  public BaseEntity getInfoByKey(Object obj);

  public Object queryObject(String sqlId, Object obj) throws Exception;

  public Object queryForObject(String sqlId, Object obj) throws Exception;

  public List<T> findBaseModeList(Object obj) throws Exception;

  public List<T> findBaseModeList(String sqlId, Object obj) throws Exception;

  public List<T> findBaseModePageList(Object obj) throws Exception;

  public List<T> findBaseModePageList(String sqlId, Object obj) throws Exception;

  public void delete(Object obj);

  public void delete(String sqlId, Object obj);

  public String getSqlMapNameSpace();

  public List<T> queryForList(Object parameterObject);

  public List<T> queryForList(String statementName, Object parameterObject);

  public List<T> queryForList(String statementName, int skipResults, int maxResults) throws Exception;

  public List<T> queryForList(String statementName, Object parameterObject, int skipResults, int maxResults)
      throws Exception;

  public Page<T> findPageList(Page<T> page, Map<String, Object> condition, String sqlId) throws Exception;

  public Page<T> findPageList(Page<T> page, Map<String, Object> condition) throws Exception;

  public int countObject(String sqlId, Object obj) throws Exception;

  public int insertBatch(List<T> dataList) throws SQLException;

  public int insertBatch(String sqlId, List<T> dataList) throws SQLException;

}
