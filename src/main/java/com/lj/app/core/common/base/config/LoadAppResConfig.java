package com.lj.app.core.common.base.config;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;



/**
 * 
 * <p>装载应用资源的配置信息</p>
 */
@SuppressWarnings("unchecked")
public class LoadAppResConfig {

	/**
	 * <p>每个应用对应一个配置文件，对应Map中的一条记录。以AppCode值作为key值</p>
	 */
	private static Map<String, AppConfigBean> appConfigMap = new HashMap<String, AppConfigBean>();
	private static Map<String,String> belongSysMap = new HashMap<String, String>();
	private static final String CONFIG_FILE_PATH_PREFIX = "config/appconfig/datasrc/";
	private static Log logger = LogFactory.getLog(LoadAppResConfig.class);
	static {
		reload();
	}
	
	/**
	 * <p>重新加载应用资源配置信息</p>
	 *
	 */
	public static void reload() {
		appConfigMap.clear();
		belongSysMap.clear();
		loadCfgFileInClassPath(CONFIG_FILE_PATH_PREFIX);
	}
	
	
	/**
	 * <p>加载所有应用资源配置文件</p>
	 *
	 */
	private static void loadCfgFileInClassPath(String filePath) 
	{
		logger.debug("filePath====" + filePath);
		if(filePath == null ){
			return ;
		}
		AppConfigBean appConfigItem = null;
		SAXBuilder builder = new SAXBuilder();
		try
	    {
			URL fileUrl = null;
			try {
				fileUrl = ResourceUtils.getURL("classpath:"+filePath);
			} catch (Exception e) {
				logger.info("filePath["+ filePath + "] is not exist" );
			}
			if(fileUrl != null && ResourceUtils.isJarURL(fileUrl)){
				logger.debug("--------this is a jar file!");
				String filepath = ResourceUtils.extractJarFileURL(fileUrl).toString();
				logger.debug("------filepath-------"+filepath);
				filepath = filepath.substring("file:".length());
				logger.debug("filepath2===="+filepath);
				JarFile jarFile = new JarFile(filepath);
				Enumeration<JarEntry> entrys = jarFile.entries();
				while(entrys.hasMoreElements()){
		            JarEntry jar = entrys.nextElement();
		            String path = jar.getName();
		            //path.substring(filePath.length()).indexOf("\/") == -1 
		            //jar包文件遍历，只在本层目录下查找，不包含子目录
		            if(path.startsWith(filePath) && 
		            		path.substring(filePath.length()).indexOf("/") == -1 
		            		&& path.endsWith(".xml")){
		            	logger.debug("now deal xml-file in jar:"+jar.getName());
		            	String appcode = null;
						String belongsys =null;
						InputStream is = jarFile.getInputStream(jar);
						if(is!=null){
			            	Document doc = builder.build(is);
							Element root = doc.getRootElement();
							appcode = root.getAttributeValue("appcode");
							belongsys  = root.getAttributeValue("belongsys");
							belongSysMap.put(appcode, belongsys);
							//解析单个文件
							appConfigItem = parseAppConfig(root);
							appConfigMap.put(appcode,appConfigItem);
						}
		            }
		        }
			}else{
				File cfgFilePath = null;
				try {
					cfgFilePath = ResourceUtils.getFile("classpath:"+filePath);
				} catch (Exception e) {
					logger.info("cfgFilePath["+ cfgFilePath + "] is not exist" );
				}
				logger.debug("cfgFilePath===" + cfgFilePath);
				if(cfgFilePath != null && cfgFilePath.exists()) {
					File[] cfgFiles = cfgFilePath.listFiles();//得到目录下的所有的配置文件
					for(int i=0; i<cfgFiles.length; i++) 
					{
						String appcode = null;
						String belongsys =null;
						String fileName = cfgFiles[i].getName();
						if(cfgFiles[i].isFile() && fileName.endsWith("xml"))
						{
							logger.debug("now deal with file===" + fileName);
							Document doc = builder.build(cfgFiles[i].toURL());
							Element root = doc.getRootElement();
							appcode = root.getAttributeValue("appcode");
							belongsys  = root.getAttributeValue("belongsys");
							//put belongsys value to belongSysMap
							belongSysMap.put(appcode, belongsys);
							//解析单个文件
							appConfigItem = parseAppConfig(root);
							appConfigMap.put(appcode,appConfigItem);
						}
					}
				}
			}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	/**
	 * <p>装载单个应用资源配置文件
	 * @param Element
	 * @return DataSrcConfigBean,返回DATASRC配置对象
	 */
	private static AppConfigBean parseAppConfig(Element root) throws Exception
	{
		AppConfigBean appConfigBean = new AppConfigBean();
		
		//装载帐号管理类型
		appConfigBean.setAcctType(root.getChildText("accttype"));
		//装载查询接口实现方式
		appConfigBean.setBatchQueryMode(root.getChildText("batchquerymode"));
		appConfigBean.setSingleQueryMode(root.getChildText("singlequerymode"));
		//装载开发模式/联调(发布)模式,不配置默认为联调模式
		String debug = root.getChildText("debug");
		appConfigBean.setDebug(debug==null?"true":debug);
		//装载接口消息体是否加密,不配置默认为false
		String encryt = root.getChildText("encryt");
		appConfigBean.setEncryt(encryt==null?"false":encryt);
		//装载视图查询方式下的默认数据源
		appConfigBean.setDefaultDataSrc(root.getChildText("defaultdatasrc"));
		//装载定期同步比较模式
		appConfigBean.setSyncMode(root.getChildText("syncmode"));
		//装载比较模式
		appConfigBean.setSyncDealMode(root.getChildText("syncdealmode"));
		//装载帐号管理Bean
		appConfigBean.setAcctBean(root.getChildText("acctbean").trim());
		//装载webService调用
		appConfigBean.setCallWebService(root.getChildText("callwebservice"));
		//装载webService调用
		appConfigBean.setAuthorRelServiceGetter(root.getChildText("authorRelServiceGetter"));
		//装载webService调用
		appConfigBean.setAuthorUpdateServiceGetter(root.getChildText("authorUpdateServiceGetter"));
		//装载授权实体管理Service
		appConfigBean.setAuthorService(root.getChildText("authorservice"));
		//装载授权实体批量管理Service
		appConfigBean.setBatchAuthorService(root.getChildText("batchauthorservice"));
		//装载帐号管理Service
		appConfigBean.setAcctService(root.getChildText("acctservice"));
		//装载批量帐号管理Service 
		appConfigBean.setBatchAcctService(root.getChildText("batchacctservice"));
		// 装载创建、最后更新时间主导方
		appConfigBean.setTimeDominant(root.getChildText("timedominant"));
		appConfigBean.setBatchfunc(root.getChildText("batchfunc"));
		//装载应用资源父应用appCode 
		if(root.getChildText("parentapp")!=null)
			appConfigBean.setParentApp(root.getChildText("parentapp"));
		//装载应用资源兄弟应用
		if(root.getChildText("brotherapp")!=null)
			appConfigBean.setBrotherApp(root.getChildText("brotherapp"));
		//装载应用资源扩展属性页面
		if(root.getChildText("acctext")!=null)
			appConfigBean.setAcctExt(root.getChildText("acctext"));
		
		//装载帐号管理接口服务发布信息
		Map<String,SoapUrlConfigBean> soapMap = new HashMap<String,SoapUrlConfigBean>();
		Element soapurlRoot = root.getChild("soapurl");
		List<Element> soapurlElements = soapurlRoot.getChildren("service");
		Iterator itsoap = soapurlElements.iterator();
		while(itsoap.hasNext()) 
		{
			Element soapurlElement = (Element)itsoap.next();
			SoapUrlConfigBean soapUrlConfigBean = new SoapUrlConfigBean();
			String service_name = soapurlElement.getAttributeValue("name");
			soapUrlConfigBean.setName(service_name);
			soapUrlConfigBean.setAuthen(soapurlElement.getAttributeValue("authen"));
			soapUrlConfigBean.setClientIp(soapurlElement.getAttributeValue("clientip"));
			soapUrlConfigBean.setUrlAddr(soapurlElement.getAttributeValue("url"));
			soapMap.put(service_name,soapUrlConfigBean);
		}
		appConfigBean.setSoapMap(soapMap);
		
		//装载嵌套页面配置信息
		List<PageSrcConfigBean> pageList = new ArrayList<PageSrcConfigBean>();
		Element pagesrcRoot = root.getChild("pagesrc");
		List pagesrcElements = pagesrcRoot.getChildren("page");
		Iterator itpage = pagesrcElements.iterator();
		while(itpage.hasNext()) 
		{
			Element pagesrcElement = (Element)itpage.next();
			PageSrcConfigBean pageSrcConfigBean = new PageSrcConfigBean();
			pageSrcConfigBean.setLinkTitle(pagesrcElement.getAttributeValue("linktitle"));
			pageSrcConfigBean.setLinkUrl(pagesrcElement.getAttributeValue("linkurl"));
			pageSrcConfigBean.setFlag(pagesrcElement.getAttributeValue("flag"));
			pageSrcConfigBean.setCode(pagesrcElement.getAttributeValue("code"));
			pageList.add(pageSrcConfigBean);
		}
		appConfigBean.setPageList(pageList);
		
		//装载附属应用配置信息
		List<SubAppConfigBean> subApp = new ArrayList<SubAppConfigBean>();
		Element subapp = root.getChild("subapp");
		if(subapp!=null){
			String subAttrId = subapp.getAttributeValue("subAttrId");
			appConfigBean.setSubAttrId(subAttrId);
			List subappElements = subapp.getChildren("sub");
			Iterator itsub = subappElements.iterator();
			while(itsub.hasNext()) 
			{
				Element subappElement = (Element)itsub.next();
				SubAppConfigBean subAppConfigBean = new SubAppConfigBean();
				subAppConfigBean.setCode(subappElement.getAttributeValue("code"));
				subAppConfigBean.setAppName(subappElement.getAttributeValue("appname"));
				subAppConfigBean.setSsoUrl(subappElement.getAttributeValue("ssourl"));
				subApp.add(subAppConfigBean);
			}
			appConfigBean.setSubApp(subApp);
		}
		//装载组织机构数据权限控制方式信息
		DataControlBean dataControlBean = new DataControlBean();
		Element datacontrolElement = root.getChild("datacontrol");
		dataControlBean.setStyle(datacontrolElement.getAttributeValue("style"));
		dataControlBean.setOrgRoot(datacontrolElement.getAttributeValue("orgroot"));
		dataControlBean.setWebUrl(datacontrolElement.getAttributeValue("weburl"));
		dataControlBean.setServicesName(datacontrolElement.getAttributeValue("servicename"));
		dataControlBean.setReqPara(datacontrolElement.getAttributeValue("reqpara"));
		dataControlBean.setRspPara(datacontrolElement.getAttributeValue("rsppara"));
		dataControlBean.setFuncClass(datacontrolElement.getAttributeValue("class"));
		appConfigBean.setDataControlBean(dataControlBean);
		
		//装载视图方式查询接口配置信息
		Element datasrcRoot = root.getChild("datasrc");
		List tableElements = datasrcRoot.getChildren("table");
		Iterator itTable = tableElements.iterator();
		Map<String, TableConfigBean> tableConfigMap = new HashMap<String, TableConfigBean>();
		while(itTable.hasNext()) 
		{
			Element tableElement = (Element)itTable.next();
			String table_type = tableElement.getAttributeValue("type");
			TableConfigBean tableConfigBean = parseTableConfig(tableElement);
			tableConfigMap.put(table_type,tableConfigBean);
		}
		appConfigBean.setTableConfigMap(tableConfigMap);
		
		//装载权限树配置
		PermissionConfigBean permissionConfigBean = null;
		Element permissionRoot = root.getChild("permission");
		if(permissionRoot != null)
		{
			permissionConfigBean = new PermissionConfigBean();
			String type = permissionRoot.getAttributeValue("type");
			String roottype = permissionRoot.getAttributeValue("roottype");
			String rootvalue = permissionRoot.getAttributeValue("rootvalue");
			permissionConfigBean.setType(type);
			permissionConfigBean.setRoottype(roottype);
			permissionConfigBean.setRootvalue(rootvalue);
		}
		appConfigBean.setPermissionConfigBean(permissionConfigBean);
		
		return appConfigBean;
	}
	
	
	/**
	 * <p>获取单个TABLE对象配置信息</p>
	 * @param Element
	 * @return TableConfigBean,返回TABLE配置对象
	 */
	private static TableConfigBean parseTableConfig(Element tableElement) throws Exception
	{
		String table_type = null;
		TableConfigBean tableConfigBean = new TableConfigBean();
		String table_name = tableElement.getAttributeValue("name");
		String sql = tableElement.getAttributeValue("sql");
		String datasrcname = tableElement.getAttributeValue("datasrcname");
		String multi_org = tableElement.getAttributeValue("multiorg");
		String del_policy = tableElement.getAttributeValue("delpolicy");
		String uap_table_name = tableElement.getAttributeValue("uaptable");
		String uap_type_code = tableElement.getAttributeValue("typecode");
		table_type = tableElement.getAttributeValue("type");
		String table_filter = null;
		table_filter = tableElement.getAttributeValue("filter");
		tableConfigBean.setName(table_name);
		tableConfigBean.setSql(sql);
		tableConfigBean.setDatasrcname(datasrcname);
		tableConfigBean.setType(table_type);
		tableConfigBean.setMultiOrg(multi_org);
		tableConfigBean.setDelPolicy(del_policy);
		tableConfigBean.setUapTable(uap_table_name);
		tableConfigBean.setTypeCode(uap_type_code);
		tableConfigBean.setFilter(table_filter);
		
		//得到单个TABLE的字段列表
		List<FieldConfigBean> list = new ArrayList<FieldConfigBean>();
		List fieldElements = tableElement.getChildren("field");
		Iterator itor = fieldElements.iterator();
		while(itor.hasNext()) 
		{
			Element fieldElement = (Element)itor.next();
			//得到某个TABLE对象的单个字段对象
			FieldConfigBean fieldConfigBean = parseFieldConfig(fieldElement);			
			list.add(fieldConfigBean);
		}
		tableConfigBean.setFieldList(list);
		return tableConfigBean;
		
	}
	
	/**
	 * <p>获取单个FIELD对象配置信息</p>
	 * @param Element
	 * @return FieldConfigBean,返回FIELD配置对象
	 */
	private static FieldConfigBean parseFieldConfig(Element fieldElement) throws Exception
	{
		FieldConfigBean fieldConfigBean = new FieldConfigBean();
		String field_name = fieldElement.getAttributeValue("name");//应用字段名称
		fieldConfigBean.setName(field_name);
		String field_type = fieldElement.getAttributeValue("type");//应用字段类型
		fieldConfigBean.setType(field_type);
		String xml_title = fieldElement.getAttributeValue("xmltitle");//定义字段通过XML报文发送时的标签
		fieldConfigBean.setXmlTitle(xml_title);
		String uap_field_name = fieldElement.getAttributeValue("uapfield");//映射到4A方的字段名称
		fieldConfigBean.setUapField(uap_field_name);
		String uap_field_type = fieldElement.getAttributeValue("uaptype");//映射到4A的字段类垄1??7
		fieldConfigBean.setUapType(uap_field_type);
		String get = fieldElement.getAttributeValue("get");//对应的Bean对象Get方法
		fieldConfigBean.setGet(get);
		String set = fieldElement.getAttributeValue("set");//对应的Bean对象Set方法
		fieldConfigBean.setSet(set);
		String lock_value = fieldElement.getAttributeValue("lockvalue");//帐号状态字段独有的描述属性：描述帐号锁定状态下的值
		fieldConfigBean.setLockValue(lock_value);
		String unlock_value = fieldElement.getAttributeValue("unlockvalue");//帐号状态字段独有的描述属性：描述帐号正常状态下的值
		fieldConfigBean.setUnlockValue(unlock_value);
		String main_field = fieldElement.getAttributeValue("mainfield");//是否是主表字段。仅对帐号表需要配置，对非主表字段标识为扩展属性
		fieldConfigBean.setMainField(main_field);
		String attr_id = fieldElement.getAttributeValue("attrid");//若是扩展属性，定义扩展属性标识
		fieldConfigBean.setAttrId(attr_id);
		String map_key = fieldElement.getAttributeValue("mapkey");//仅对帐号表需要配置，一般般指帐号标识
		String resultColumn = fieldElement.getAttributeValue("resultcolumn"); //仅对case when 取别名有用
		fieldConfigBean.setResultColumn(resultColumn);
		fieldConfigBean.setMapKey(map_key);
		return fieldConfigBean;
	}
	
	/**
	 * <p>获取所有应用的配置信息</p>
	 * @return appConfigMap
	 */
	public static Map<String, AppConfigBean> getAllAppCofig() {
		return appConfigMap;
	}
	
	/**
	 * <p>获取单个应用的配置信息</p>
	 * @param appCode
	 * @return AppConfigBean
	 */
	public static AppConfigBean getAppCofig(String appCode) {
		if(appConfigMap.get(appCode)==null){
			logger.warn("cann't find config file of "+appCode+", please check configs or check config files in uap!");
			return new AppConfigBean();
		}else{
			return appConfigMap.get(appCode);
		}
	}
	
	/**
	 * <p>获取单个应用的帐号管理方式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAcctType(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAcctType()==null){
			logger.warn("cann't find [accttype] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getAcctType();
	}
	
	/**
	 * <p>获取单个应用的帐号查询接口实现方式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getSingleQueryMode(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getSingleQueryMode()==null){
			logger.warn("cann't find [singlequerymode] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getSingleQueryMode();
	}
	
	/**
	 * <p>获取单个应用的帐号查询接口实现方式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getBatchQueryMode(String appCode) throws Exception{
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getBatchQueryMode()==null){
			logger.warn("cann't find [batchquerymode] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getBatchQueryMode();
	}
	
	/**
	 * <p>获取单个应用的DEBUG/DEPLOY模式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getDebug(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getDebug()==null){
			logger.warn("cann't find [debug] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getDebug();
	}
	
	/**
	 * <p>获取单个应用的接口消息是否加密</p>
	 * @param appCode
	 * @return String
	 */
	public static String getEncryt(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getEncryt()==null){
			logger.warn("cann't find [encryt] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getEncryt();
	}
	
	/**
	 * <p>获取单个应用的帐号同步比较方式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getSyncMode(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getSyncMode()==null){
			logger.warn("cann't find [syncmode] from ["+appCode+"]'s config file,default it to [A], please check !!!");
			return "A";
		}else{
			return appConfigBean.getSyncMode();
		}
	}
	
	/**
	 * <p>获取单个应用的帐号同步处理方式</p>
	 * @param appCode
	 * @return String
	 */
	public static String getSyncDealMode(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getSyncDealMode()==null){
			logger.warn("cann't find [syncdealmode] from ["+appCode+"]'s config file,default it to [1], please check !!!");
			return "1";
		}else{
			return appConfigBean.getSyncDealMode();
		}
	}
	
	/**
	 * <p>获取单个应用的视图查询接口方式下应用侧的数据源</p>
	 * @param appCode
	 * @return String
	 */
	public static String getDefaultDataSrc(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getDefaultDataSrc()==null){
			logger.warn("cann't find [defaultdatasrc] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getDefaultDataSrc();
	}
	
	/**
	 * <p>获取单个应用的帐号管理Bean类</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAcctBean(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAcctBean()==null){
			logger.warn("cann't find [acctbean] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getAcctBean();
	}
	
	/**
	 * <p>获取单个应用的web服务调用service类</p>
	 * @param appCode
	 * @return String
	 */
	public static String getCallWebService(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getCallWebService()==null){
			logger.info("cann't find [callwebservice] from ["+appCode+"]'s config file,user AppCallWsApiServiceImpl!!!");
		}
		return appConfigBean.getCallWebService();
	}
	
	/**
	 * <p>获取单个应用的授权实体关系变更服务类获取服务</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAuthorRelServiceGetter(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAuthorRelServiceGetter()==null){
			logger.info("cann't find [authorRelServiceGetter] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getAuthorRelServiceGetter();
	}
	
	/**
	 * <p>获取单个应用的授权实体变更服务类获取服务</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAuthorUpdateServiceGetter(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAuthorUpdateServiceGetter()==null){
			logger.info("cann't find [authorUpdateServiceGetter] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getAuthorUpdateServiceGetter();
	}
	
	/**
	 * <p>获取单个应用的授权实体管理Service类</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAuthorService(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAuthorService()==null){
			logger.info("cann't find [authorservice] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getAuthorService();
	}
	
	/**
	 * <p>获取单个应用的授权实体管理Service类</p>
	 * @param appCode
	 * @return String
	 */
	public static String getBatchAuthorService(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getBatchAuthorService()==null){
			logger.info("cann't find [batchauthorservice] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getBatchAuthorService();
	}
	
	/**
	 * <p>获取单个应用的创建、最后更新时间主导方</p>
	 * @param appCode
	 * @return String APP-以应用为主导，其它以4A为主导
	 */
	public static String getTimeDominant(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getTimeDominant()==null){
			logger.info("cann't find [TimeDominant] from ["+appCode+"]'s config file, use default[4A] !!!");
		}
		return appConfigBean.getTimeDominant();
	}
	
	/**
	 * <p>获取单个应用的帐号管理Service类</p>
	 * @param appCode
	 * @return String
	 */
	public static String getAcctService(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAcctService()==null){
			logger.info("cann't find [acctservice] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getAcctService();
	}
	
	/**
	 * 获取批量应用的帐号管理Service类
	 * @param appCode
	 * @return
	 */
	public static String getBatchAcctService(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getBatchAcctService()==null){
			logger.info("cann't find [batchacctservice] from ["+appCode+"]'s config file, user default !!!");
		}
		return appConfigBean.getBatchAcctService();
	}
	
	/**
	 * 获取批量应用的帐号管理Service类
	 * @param appCode
	 * @return
	 */
	public static String getBatchFuc(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getBatchfunc()==null){
			logger.warn("can't find [batchfuc] from ["+appCode+"] 's  config file ");
		}else if(appConfigBean.getBatchfunc().equals("open")){
			logger.info("appcode will open the function of account's batch ");
		}
		return appConfigBean.getBatchfunc();
	}
	
	
	/**
	 * 获取单个应用资源父应用code
	 * @param appCode
	 * @return
	 */
	public static String getParentCode(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getParentApp()==null&&"E".equals(appConfigBean.getAcctType())){
			logger.warn("cann't find [parentapp] from ["+appCode+"]'s config file which accttype is [E], please check !!!");
		}
		return appConfigBean.getParentApp();
	}
	
	/**
	 * 获取单个应用资源兄弟应用code
	 * @param appCode
	 * @return
	 */
	public static String getBrotherApp(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getBrotherApp()==null&&"F".equals(appConfigBean.getAcctType())){
			logger.warn("cann't find [brotherapp] from ["+appCode+"]'s config file which accttype is [F], please check !!!");
		}
		return appConfigBean.getBrotherApp();
	}
	
	/**
	 * 获取F类型应用护属性页面
	 * @param appCode
	 * @return
	 */
	public static String getAcctExt(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getAcctExt()==null&&"F".equals(appConfigBean.getAcctType())){
			logger.warn("cann't find [acctext] from ["+appCode+"]'s config file which accttype is [F], please check !!!");
		}
		return appConfigBean.getAcctExt();
	}
	
	/**
	 * <p>获取单个应用的接口服务配置信息</p>
	 * @param AppConfigBean
	 * @return List<SoapUrlConfigBean>
	 */
	public static Map<String,SoapUrlConfigBean> getSoapUrlCofigList(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		return appConfigBean.getSoapMap();
	}
	
	/**
	 * <p>获取单个应用的嵌套页面配置信息</p>
	 * @param AppConfigBean
	 * @return List<PageSrcConfigBean>
	 */
	public static List<PageSrcConfigBean> getPageSrcCofigList(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		return appConfigBean.getPageList();
	}
	
	/**
	 * 获取单个应用的附属应用对应的attrId
	 * @param appCode
	 * @return
	 */
	public static String getSubAttrId(String appCode){
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getSubAttrId()==null&&"D".equals(appConfigBean.getAcctType())){
			logger.warn("cann't find [subattrId] from ["+appCode+"]'s config file which accttype is [D], please check !!!");
		}
		return appConfigBean.getSubAttrId();
	}
	
	/**
	 * <p>获取单个应用的附属应用配置信息</p>
	 * @param AppConfigBean
	 * @return List<PageSrcConfigBean>
	 */
	public static List<SubAppConfigBean> getSubAppCofigList(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getSubApp()==null&&"D".equals(appConfigBean.getAcctType())){
			logger.warn("cann't find [subapp] from ["+appCode+"]'s config file which accttype is [D], please check !!!");
		}
		return appConfigBean.getSubApp();
	}
	
	/**
	 * <p>获取某个应用的实体内授权嵌套页面，用于显示在编辑帐号的实体内授权嵌套页面内</p>
	 * @param AppConfigBean
	 * @return List<PageSrcConfigBean>
	 */
	public static PageSrcConfigBean getPageSrcCofig(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		List<PageSrcConfigBean> pageSrcConfigBeanList =  appConfigBean.getPageList();
		PageSrcConfigBean pageSrcConfigBean = null;
		int ii = pageSrcConfigBeanList.size();
		for(int i=0;i<ii;i++)
		{
			pageSrcConfigBean = (PageSrcConfigBean)pageSrcConfigBeanList.get(i);
			if(pageSrcConfigBean.getFlag()!=null&&pageSrcConfigBean.equals("1"))
			{
				break;
			}
		}
		return pageSrcConfigBean;
	}
	
	/**
	 * <p>获取单个应用的组织机构数据权限控制方式配置信息</p>
	 * @param AppConfigBean
	 * @return List<PageSrcConfigBean>
	 */
	public static DataControlBean getDataControlBean(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getDataControlBean()==null){
			logger.warn("cann't find [datacontrol] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getDataControlBean();
	}
	
	/**
	 * <p>获取单个应用的数据表配置信息</p>
	 * @param AppConfigBean
	 * @return Map<String, TableConfigBean>
	 */
	public static Map<String, TableConfigBean> getTableConfigMap(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		return  appConfigBean.getTableConfigMap();
	}
	
	public static FieldConfigBean getFieldConfigBean(String appCode) {
		FieldConfigBean fieldConfigBean = null;
		Map<String, TableConfigBean> tableConfigMap = getTableConfigMap(appCode);
		TableConfigBean tableConfig = (TableConfigBean)tableConfigMap.get("USER");
		List<FieldConfigBean> fieldlist = tableConfig.getFieldList();
		Iterator<FieldConfigBean> it = fieldlist.iterator();
		while(it.hasNext()) {	
			fieldConfigBean = (FieldConfigBean)it.next();
			String uap_field_name = fieldConfigBean.getUapField();
			if(uap_field_name.equalsIgnoreCase("acct_status"))
			{
				break;
			}
		}
		return fieldConfigBean;
	}
	
	public static String getDataSrcName(String appCode,String tableType) {
		Map<String, TableConfigBean> tableConfigMap = getTableConfigMap(appCode);
		TableConfigBean tableConfig = (TableConfigBean)tableConfigMap.get(tableType);
		if(tableConfig!=null)
			return tableConfig.getDatasrcname();
		else
			return null;
	}
	
	public static String getAcctDelPolicy(String appCode) {
		Map<String, TableConfigBean> tableConfigMap = getTableConfigMap(appCode);
		TableConfigBean tableConfig = (TableConfigBean)tableConfigMap.get("USER");
		//如果不配置该属性，默认为Y，代表帐号删除策略为物理删除
		return tableConfig.getDelPolicy()==null?"Y":tableConfig.getDelPolicy();
	}
	
	public static String getAcctMultiOrg(String appCode) {
		Map<String, TableConfigBean> tableConfigMap = getTableConfigMap(appCode);
		TableConfigBean tableConfig = (TableConfigBean)tableConfigMap.get("USER");
		//如果不配置该属性，默认为N，代表一个帐号归属一个组织机构
		return tableConfig.getMultiOrg()==null?"N":tableConfig.getDelPolicy();
	}
	
	public static PermissionConfigBean getPermissionConfigBean(String appCode) {
		AppConfigBean appConfigBean = getAppCofig(appCode);
		if(appConfigBean.getPermissionConfigBean()==null){
			logger.warn("cann't find [permission] from ["+appCode+"]'s config file, please check !!!");
		}
		return appConfigBean.getPermissionConfigBean();
	}
	
	public static String getBelongSys(String appCode){
		if(belongSysMap.get(appCode)==null){
			logger.warn("cann't find [belongsys] from ["+appCode+"]'s config file, please check !!!");
		}
		return belongSysMap.get(appCode);
	}


	public static Map<String, AppConfigBean> getAppConfigMap() {
		return appConfigMap;
	}

	public static void setAppConfigMap(Map<String, AppConfigBean> appConfigMap) {
		LoadAppResConfig.appConfigMap = appConfigMap;
	}
	
}
