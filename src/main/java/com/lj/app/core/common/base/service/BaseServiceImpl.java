package com.lj.app.core.common.base.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.base.dao.BaseDao;
import com.lj.app.core.common.base.entity.BaseEntity;
import com.lj.app.core.common.pagination.Page;
import com.lj.app.core.common.util.MapAndObject;
import com.lj.app.core.common.util.StringUtil;

@Service("baseService")
public abstract class BaseServiceImpl<T> implements BaseService {

  protected static Logger log = Logger.getLogger(BaseService.class);

  private static final String NAMESPACE_SPLIT = ".";
  private static final String PAGE_QUERY_SUBFIX = "_count";
  private static final String DEFAULT_PAGENATION_NAME = "pagenate";

  @Autowired
  private BaseDao<T> baseDao;

  @Override
  public void insertObject(Object obj) throws Exception {
    insertObject("insert", obj);
  }

  @Override
  public void insertObject(String sqlid, Object obj) throws Exception {
    baseDao.insertObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlid, obj);
  }

  @Override
  public int insertObjectReturnKey(String sqlid, Object obj) throws Exception {
    return baseDao.insertObjectReturnKey(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlid, obj);
  }

  @Override
  public int insertObjectReturnKey(Object obj) throws Exception {
    return baseDao.insertObjectReturnKey(getSqlMapNameSpace() + NAMESPACE_SPLIT + "insert", obj);
  }

  @Override
  public Object insertObjectReturn(String sqlid, Object obj) throws Exception {
    return baseDao.insertObjectReturn(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlid, obj);
  }

  @Override
  public Object insertObjectReturn(Object obj) throws Exception {
    return baseDao.insertObjectReturn(getSqlMapNameSpace() + NAMESPACE_SPLIT + "insert", obj);
  }

  @Override
  public void updateObject(Object obj) throws Exception {
    updateObject("update", obj);
  }

  @Override
  public void updateObject(String sqlId, Object obj) throws Exception {
    baseDao.updateObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public void saveOrUpdate(Object obj, Object id) throws Exception {
    saveOrUpdate(obj, id);
  }

  @Override
  public void saveOrUpdate(String sqlId, Object obj, Object id) throws Exception {
    if (id != null) {
      baseDao.updateObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
    } else {
      baseDao.insertObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
    }
  }

  @Override
  public BaseEntity findObject(String sqlId, Object obj) throws Exception {
    return baseDao.findObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public BaseEntity getInfoByKey(String sqlId, Object obj) {
    return baseDao.getInfoByKey(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public BaseEntity getInfoByKey(Object obj) {
    return baseDao.getInfoByKey(getSqlMapNameSpace() + NAMESPACE_SPLIT + "getInfoByKey", obj);
  }

  @Override
  public Object queryObject(String sqlId, Object obj) throws Exception {
    return baseDao.findObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public Object queryForObject(String sqlId, Object obj) throws Exception {
    return baseDao.queryForObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public List<BaseEntity> findBaseModeList(Object obj) throws Exception {
    return findBaseModeList("select", obj);
  }

  @Override
  public List<BaseEntity> findBaseModeList(String sqlId, Object obj) throws Exception {
    return baseDao.findBaseModeList(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public List<BaseEntity> findBaseModePageList(Object obj) throws Exception {
    return findBaseModePageList("select", obj);
  }

  @Override
  public List<BaseEntity> findBaseModePageList(String sqlId, Object obj) throws Exception {
    return baseDao.findBaseModePageList(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  public void delete(Object obj) {
    delete("delete", obj);
  }

  @Override
  public void delete(String sqlId, Object obj) {
    baseDao.deleteObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @Override
  @SuppressWarnings("all")
  public String getSqlMapNameSpace() {
    String nameSpace = "";
    try {
      Class clazz = getClass();
      String clazzName = clazz.getName();
      int startPos = clazzName.lastIndexOf(".");
      if (startPos != -1) {
        int endPost = clazzName.length() - "serviceImpl".length();
        nameSpace = clazzName.substring(startPos + 1, endPost);
        nameSpace = nameSpace.substring(0, 1).toLowerCase() + nameSpace.substring(1);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return nameSpace;
  }

  @Override
  @SuppressWarnings("all")
  public int insertBatch(List dataList) throws SQLException {
    return insertBatch("insert", dataList);
  }

  @Override
  @SuppressWarnings("all")
  public int insertBatch(String sqlId, List dataList) throws SQLException {
    return baseDao.insertBatch(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, dataList);

  }

  @Override
  @SuppressWarnings("all")
  public List queryForList(Object parameterObject) {
    return queryForList("select", parameterObject);
  }

  @Override
  @SuppressWarnings("all")
  public List queryForList(String statementName, Object parameterObject) {
    return baseDao.queryForList(getSqlMapNameSpace() + NAMESPACE_SPLIT + statementName, parameterObject);
  }

  @Override
  @SuppressWarnings("all")
  public List queryForList(String statementName, int skipResults, int maxResults) throws DataAccessException {
    return baseDao.queryForList(statementName, skipResults, maxResults);
  }

  @Override
  @SuppressWarnings("all")
  public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults)
      throws DataAccessException {
    return baseDao.queryForList(statementName, parameterObject, skipResults, maxResults);
  }

  @Override
  @SuppressWarnings("all")
  public Page findPageList(Page page, Map condition, String sqlId) throws Exception {
    String countQuery = getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId + PAGE_QUERY_SUBFIX;
    String findQuery = getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId;

    if (StringUtil.isNotBlank(page.getSortColumns())) {
      condition.put("sortColumns", page.getSortColumns());
    }

    Map parameterObject = new MapAndObject(condition, page.getFilters());

    Number totalCount = (Number) baseDao.queryForObject(countQuery, parameterObject);
    if (totalCount == null || totalCount.intValue() <= 0) {
      return page;
    }

    page.setTotalCount(totalCount.intValue());
    List list = baseDao.queryForList(findQuery, parameterObject, page.getFirstResult(), page.getPageSize());
    page.setResult(list);
    return page;
  }

  @Override
  @SuppressWarnings("all")
  public Page findPageList(Page page, Map condition) throws Exception {
    return findPageList(page, condition, DEFAULT_PAGENATION_NAME);
  }

  @Override
  public int countObject(String sqlId, Object obj) throws Exception {
    return baseDao.countObject(getSqlMapNameSpace() + NAMESPACE_SPLIT + sqlId, obj);
  }

  @SuppressWarnings("all")
  public BaseDao getBaseDao() {
    return baseDao;
  }

  @SuppressWarnings("all")
  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

}
