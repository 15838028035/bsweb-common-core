package com.lj.app.core.common.base.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.lj.app.core.common.base.entity.BaseEntity;
import com.lj.app.core.common.pagination.Page;

@SuppressWarnings("all")
public interface BaseService<T> {
	
	public void insertObject(Object obj);

	public void insertObject(String sqlId, Object obj);
	
	 public int insertObjectReturnKey(String sqlid, Object obj);
	 public int insertObjectReturnKey(Object obj);

	public void updateObject(Object obj);

	public void updateObject(String sqlId, Object obj);
	
	public void saveOrUpdate(Object obj,Object id);
	public void saveOrUpdate(String sqlId, Object obj, Object id);

	public BaseEntity findObject(String sqlId, Object obj);

	public BaseEntity getInfoByKey(String sqlId, Object obj);

	public BaseEntity getInfoByKey(Object obj);
	
	public Object queryObject(String sqlId, Object obj);
	
	public Object  queryForObject(String sqlId, Object obj);

	public List<T> findBaseModeList(Object obj);

	public List<T> findBaseModeList(String sqlId, Object obj);

	public List<T> findBaseModePageList(Object obj);

	public List<T> findBaseModePageList(String sqlId, Object obj);

	public void delete(Object obj);

	public void delete(String sqlId, Object obj);

	public String getSqlMapNameSpace();

	public List<T> queryForList(Object parameterObject) throws DataAccessException;

	public List<T> queryForList(String statementName, Object parameterObject);

	public List<T> queryForList(String statementName, int skipResults,	int maxResults) throws Exception;

	public List<T> queryForList(String statementName, Object parameterObject,int skipResults, int maxResults) throws Exception;

	public Page<T> findPageList(Page<T> page,Map<String,Object> condition, String sqlId);
	
	public Page<T> findPageList(Page<T> page,Map<String,Object> condition);
	
	public int countObject(String sqlId, Object obj);
	
	public int insertBatch(List<T> dataList) throws SQLException;
	    
	public int insertBatch(String sqlId,List<T> dataList) throws SQLException;
	
}
