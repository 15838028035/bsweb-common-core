package com.lj.app.core.common.base.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lj.app.core.common.base.model.BaseModel;

public interface BaseDao<T>{

	 public SqlMapClient getSqlMapClientProfile() ;
	 
	 public SqlMapClientTemplate getSqlMapClientTemplateProfile();
	 
	 public void insertObject(String sqlid, Object obj);
	 
	 public int insertObjectReturnKey(String sqlid, Object obj);
	 public int insertObjectReturnKey(Object obj);

	 public void updateObject(String sqlId, Object obj);

	 public void deleteObject(String sqlId, Object obj);

	 public BaseModel findObject(String sqlId, Object obj);
	
	 public Object queryForObject(String sqlId, Object obj);

	 public BaseModel getInfoByKey(String sqlId, Object obj);

	 public List<BaseModel> findBaseModeList(String sqlId, Object obj);

	 public List<BaseModel> findBaseModePageList(String sqlId, Object obj);
	
    public List queryForList(String statementName) throws DataAccessException ;
    public List queryForList(String statementName, Object parameterObject);
    
    public List queryForList(String statementName, int skipResults, int maxResults) throws DataAccessException;
    
    public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults)
    	    throws DataAccessException;
    
    public int countObject(String sqlId, Object obj);
    
    /**
     * 批量添加
     * Title: insertBatch
     * @author zhangtq
     * <p>Description: </p>
     * @param dataList dataList
     * @return int
     * @throws SQLException
     * int
     */
    public int insertBatch(List dataList) throws SQLException;
    
    public int insertBatch(String sqlId,List dataList) throws SQLException;

}
