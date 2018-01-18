package com.lj.app.core.common.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lj.app.core.common.pagination.Page;
import com.lj.app.core.common.util.CurrentDb;
import com.lj.app.core.common.util.MapAndObject;
import com.lj.app.core.common.util.SpringContextHolder;

public abstract class BaseIbatisDao<E, PK> extends SqlMapClientDaoSupport {
  public abstract Class getEntityClass();

  public abstract void saveOrUpdate(E paramE);

  @Autowired(required = true)
  public void setSqlMapClientWorkAround(SqlMapClient sqlMapClient) {
    setSqlMapClient(sqlMapClient);
  }

  private String getCountQuery() {
    return getSimpleName() + ".count";
  }

  private String getExampleQuery() {
    return getSimpleName() + ".findByExample";
  }

  private String getSimpleName() {
    return getEntityClass().getSimpleName();
  }

  public E findById(PK primaryKey) {
    String stmtId = getSimpleName() + ".findById";
    return (E) getSqlMapClientTemplate().queryForObject(stmtId, primaryKey);
  }

  /**
   * 查找
   * @param entity 实体
   * @param sortColumns 排序列
   * @return 实体列表
   */
  public List<E> findByExample(E entity, String sortColumns) {
    Map otherFilters = new HashMap();
    otherFilters.put("sortColumns", sortColumns);
    Map parameterObject = new MapAndObject(otherFilters, entity);
    return getSqlMapClientTemplate().queryForList(getExampleQuery(), parameterObject);
  }

  public List<E> findByExample(E entity) {
    return findByExample(entity, null);
  }

  public void deleteById(PK id) {
    String stmtId = getSimpleName() + ".deleteById";
    getSqlMapClientTemplate().delete(stmtId, id);
  }

  public void deleteByExample(E entity) {
    String stmtId = getSimpleName() + ".deleteByExample";
    getSqlMapClientTemplate().delete(stmtId, entity);
  }

  /**
   * 保存对象
   * @param e 对象
   * @return 对象
   */
  public PK save(Object e) {
    String currentDb = ((CurrentDb) SpringContextHolder.getBean("currentDb")).getCurrentDb();

    String stmtId = getSimpleName() + ".save" + currentDb;
    return (PK) getSqlMapClientTemplate().insert(stmtId, e);
  }

  public int update(E entity) {
    String stmtId = getSimpleName() + ".update";
    return getSqlMapClientTemplate().update(stmtId, entity);
  }

  /**
   * 批量保存
   * @param entities 实体
   * @throws SQLException SQL异常
   */
  public void saveBatch(List<E> entities) throws SQLException {
    getSqlMapClient().startBatch();
    for (Iterator i$ = entities.iterator(); i$.hasNext();) {
      Object e = i$.next();
      save(e);
    }
    getSqlMapClient().executeBatch();
  }

  public Page pageQuery(Page page) {
    Number totalCount = (Number) getSqlMapClientTemplate().queryForObject(getCountQuery(), page.getFilters());
    if ((totalCount == null) || (totalCount.intValue() <= 0)) {
      return page;
    }
    Map otherFilters = new HashMap();
    otherFilters.put("sortColumns", page.getSortColumns());

    page.setTotalCount(totalCount.intValue());
    Map parameterObject = new MapAndObject(otherFilters, page.getFilters());
    List list = getSqlMapClientTemplate().queryForList(getExampleQuery(), parameterObject, page.getFirstResult(),
        page.getPageSize());
    page.setResult(list);
    return page;
  }
}