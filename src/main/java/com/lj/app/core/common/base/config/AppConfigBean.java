package com.lj.app.core.common.base.config;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>整个应用资源的配置对象信息</p>
 */

public class AppConfigBean {
	private String acctType;								//应用帐号管理模式
	private String batchQueryMode;							//应用资源批量查询接口模式
	private String singleQueryMode;							//应用资源單條查询接口模式
	private String debug;									//开发模式/联调(发布)模式 
	private String encryt;									//接口消息体是否加密
	private String syncMode;								//应用资源定期同步比较模式
	private String syncDealMode;							//应用资源比较处理模式
	private String defaultDataSrc;							//应用资源视图查询接口的默认数据源
	private String acctBean;								//应用资源帐号管理Bean
	private String acctService;								//应用资源帐号管理Service
	private String authorService;							//应用资源授权实体管理Service
	private String batchAuthorService;						//应用资源授权实体批量管理Service
	private String callWebService;							//应用资源WebService调用实现类
	private String authorRelServiceGetter;					//应用资源授权实体关系变更服务类获取服务
	private String authorUpdateServiceGetter;				//应用资源授权实体变更服务类获取服务
	private String batchAcctService;						//资源资源批量帐号管理Service
	private String parentApp;								//应用资源父应用编码
	private String brotherApp;								//应用资源兄弟应用编码
	private String acctExt;									//应用资源扩展属性页面路径
	private String subAttrId;								//应用资源附属应用扩展属性ID
	private List<SubAppConfigBean> subApp;					//应用资源附属应用详细信息
	private Map<String,SoapUrlConfigBean> soapMap;			//应用资源接口服务定义
	private List<PageSrcConfigBean> pageList;				//应用资源对应的嵌套页面列表
	private DataControlBean dataControlBean;				//应用资源对应的组织机构数据权限控制方式
	private Map<String,TableConfigBean> tableConfigMap;		//对应的查询视图列表
	private PermissionConfigBean permissionConfigBean;		//对应权限结构配置
	
	/**
	 * 创建、最后变更时间的主导方：4A-以4A请求报文TIMESTAMP为准；APP-以应用返回报文TIMESTAMP为准
	 */
	private String TimeDominant ;
	
	private String batchfunc;  //开放批量功能开关
	
		
	public String getBatchfunc() {
		return batchfunc;
	}

	public void setBatchfunc(String batchfunc) {
		this.batchfunc = batchfunc;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	
	public String getBatchQueryMode() {
		return batchQueryMode;
	}

	public void setBatchQueryMode(String batchQueryMode) {
		this.batchQueryMode = batchQueryMode;
	}

	public String getSingleQueryMode() {
		return singleQueryMode;
	}

	public void setSingleQueryMode(String singleQueryMode) {
		this.singleQueryMode = singleQueryMode;
	}
	
	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getEncryt() {
		return encryt;
	}

	public void setEncryt(String encryt) {
		this.encryt = encryt;
	}

	public String getSyncMode() {
		return syncMode;
	}

	public void setSyncMode(String syncMode) {
		this.syncMode = syncMode;
	}
	
	public String getDefaultDataSrc() {
		return defaultDataSrc;
	}

	public void setDefaultDataSrc(String defaultDataSrc) {
		this.defaultDataSrc = defaultDataSrc;
	}
	
	public String getAcctBean() {
		return acctBean;
	}

	public void setAcctBean(String acctBean) {
		this.acctBean = acctBean;
	}
	
	public String getAcctService() {
		return acctService;
	}

	public void setAcctService(String acctService) {
		this.acctService = acctService;
	}
	
	public Map<String,SoapUrlConfigBean> getSoapMap() {
		return soapMap;
	}

	public void setSoapMap(Map<String,SoapUrlConfigBean> soapMap) {
		this.soapMap = soapMap;
	}
	
	public List<PageSrcConfigBean> getPageList() {
		return pageList;
	}

	public void setPageList(List<PageSrcConfigBean> pageList) {
		this.pageList = pageList;
	}
	
	public DataControlBean getDataControlBean() {
		return dataControlBean;
	}

	public void setDataControlBean(DataControlBean dataControlBean) {
		this.dataControlBean = dataControlBean;
	}
	
	public Map<String,TableConfigBean> getTableConfigMap() {
		return tableConfigMap;
	}

	public void setTableConfigMap(Map<String,TableConfigBean> tableConfigMap) {
		this.tableConfigMap = tableConfigMap;
	}

	public String getBatchAcctService() {
		return batchAcctService;
	}

	public void setBatchAcctService(String batchAcctService) {
		this.batchAcctService = batchAcctService;
	}

	public PermissionConfigBean getPermissionConfigBean() {
		return permissionConfigBean;
	}

	public void setPermissionConfigBean(PermissionConfigBean permissionConfigBean) {
		this.permissionConfigBean = permissionConfigBean;
	}

	public List<SubAppConfigBean> getSubApp() {
		return subApp;
	}

	public void setSubApp(List<SubAppConfigBean> subApp) {
		this.subApp = subApp;
	}

	public String getParentApp() {
		return parentApp;
	}

	public void setParentApp(String parentApp) {
		this.parentApp = parentApp;
	}

	public String getSubAttrId() {
		return subAttrId;
	}

	public void setSubAttrId(String subAttrId) {
		this.subAttrId = subAttrId;
	}

	public String getBrotherApp() {
		return brotherApp;
	}

	public void setBrotherApp(String brotherApp) {
		this.brotherApp = brotherApp;
	}

	public String getAcctExt() {
		return acctExt;
	}

	public void setAcctExt(String acctExt) {
		this.acctExt = acctExt;
	}

	public String getSyncDealMode() {
		return syncDealMode;
	}

	public void setSyncDealMode(String syncDealMode) {
		this.syncDealMode = syncDealMode;
	}

	public String getCallWebService() {
		return callWebService;
	}

	public void setCallWebService(String callWebService) {
		this.callWebService = callWebService;
	}

	public String getAuthorRelServiceGetter() {
		return authorRelServiceGetter;
	}

	public void setAuthorRelServiceGetter(String authorRelServiceGetter) {
		this.authorRelServiceGetter = authorRelServiceGetter;
	}

	public String getAuthorUpdateServiceGetter() {
		return authorUpdateServiceGetter;
	}

	public void setAuthorUpdateServiceGetter(String authorUpdateServiceGetter) {
		this.authorUpdateServiceGetter = authorUpdateServiceGetter;
	}

	public String getAuthorService() {
		return authorService;
	}

	public void setAuthorService(String authorService) {
		this.authorService = authorService;
	}

	public String getBatchAuthorService() {
		return batchAuthorService;
	}

	public void setBatchAuthorService(String batchAuthorService) {
		this.batchAuthorService = batchAuthorService;
	}

	public String getTimeDominant() {
		return TimeDominant;
	}

	public void setTimeDominant(String TimeDominant) {
		this.TimeDominant = TimeDominant;
	}
}
