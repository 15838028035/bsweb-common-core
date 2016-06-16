package com.lj.app.core.common.base.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class LoadAppResConfigTest {

	@Test
	public void mainTest() {
		//LoadAppResConfig.loadCfgFileInClassPath();
		Set<String> appConfigSet = LoadAppResConfig.getAppConfigMap().keySet();
		Iterator<String> itSrc = appConfigSet.iterator();
		while(itSrc.hasNext()) 
		{	
			String appCode = (String)itSrc.next();
			System.out.println("###################appCode################====="+appCode);
			//AppConfigBean appConfigBean = (AppConfigBean)appConfigMap.get(appCode);
			
			System.out.println("@@@accttype==="+LoadAppResConfig.getAcctType(appCode));
			System.out.println("@@@querymode==="+LoadAppResConfig.getSingleQueryMode(appCode));
			//System.out.println("@@@querymode==="+getBatchQueryMode(appCode));
			System.out.println("@@@debug==="+LoadAppResConfig.getDebug(appCode));
			System.out.println("@@@encypt==="+LoadAppResConfig.getEncryt(appCode));
			System.out.println("@@@defaultdatasrc==="+LoadAppResConfig.getSyncMode(appCode));
			System.out.println("@@@syncmode==="+LoadAppResConfig.getSyncMode(appCode));
			System.out.println("@@@acctbean==="+LoadAppResConfig.getAcctBean(appCode));
			System.out.println("@@@acctservice==="+LoadAppResConfig.getAcctService(appCode));
			System.out.println("@@@batchAcctservice==="+LoadAppResConfig.getBatchAcctService(appCode));
			System.out.println("@@@parentapp==="+LoadAppResConfig.getParentCode(appCode));
			System.out.println("@@@subAttrId==="+LoadAppResConfig.getSubAttrId(appCode));
			
			System.out.println("################### SOAP_URL ################=====");
			Map<String,SoapUrlConfigBean> soapMap  = LoadAppResConfig.getSoapUrlCofigList(appCode);
			Set kset = soapMap.keySet();
			Iterator<String> itSoap = kset.iterator();
			while(itSoap.hasNext()) {
				String service_name = (String)itSoap.next();
				SoapUrlConfigBean soapUrlConfigBean = (SoapUrlConfigBean)soapMap.get(service_name);
				System.out.println("	@@@name==="+soapUrlConfigBean.getName());
				System.out.println("	@@@authen==="+soapUrlConfigBean.getAuthen());
				System.out.println("	@@@client_ip==="+soapUrlConfigBean.getClientIp());
				System.out.println("	@@@url_addr==="+soapUrlConfigBean.getUrlAddr());
			}
			
			System.out.println("################### PAGE_SRC ################=====");
			List<PageSrcConfigBean> pageList  = LoadAppResConfig.getPageSrcCofigList(appCode);
			Iterator<PageSrcConfigBean> itPage = pageList.iterator();
			while(itPage.hasNext()) {
				PageSrcConfigBean pageSrcConfigBean = (PageSrcConfigBean)itPage.next();
				System.out.println("	@@@link_title==="+pageSrcConfigBean.getLinkTitle());
				System.out.println("	@@@link_url==="+pageSrcConfigBean.getLinkUrl());
			}
			
			System.out.println("################### SUBAPP_SRC ################=====");
			List<SubAppConfigBean> subAppList  = LoadAppResConfig.getSubAppCofigList(appCode);
			if(subAppList!=null){
				Iterator<SubAppConfigBean> itSubApp = subAppList.iterator();
				while(itSubApp.hasNext()) {	
					SubAppConfigBean subAppConfigBean = (SubAppConfigBean)itSubApp.next();
					System.out.println("	@@@code==="+subAppConfigBean.getCode());
					System.out.println("	@@@appName==="+subAppConfigBean.getAppName());
					System.out.println("	@@@ssourl==="+subAppConfigBean.getSsoUrl());
				}
			}
			System.out.println("################### DATA_CONTROL ################=====");
			DataControlBean dataControlBean =LoadAppResConfig.getDataControlBean(appCode);
			System.out.println("	@@@style==="+dataControlBean.getStyle());
			System.out.println("	@@@service_name==="+dataControlBean.getServicesName());
			System.out.println("	@@@web_url==="+dataControlBean.getWebUrl());
			System.out.println("	@@@req_para==="+dataControlBean.getReqPara());
			System.out.println("	@@@rsp_para==="+dataControlBean.getRspPara());
			System.out.println("	@@@class==="+dataControlBean.getFuncClass());
			
			System.out.println("################### DATA_SRC ################=====");
			Map<String, TableConfigBean> tableConfigMap = LoadAppResConfig.getTableConfigMap(appCode);
			Set<String> set = tableConfigMap.keySet();
			Iterator<String> itor = set.iterator();
			while(itor.hasNext()) 
			{	
				String table_type = (String)itor.next();
				TableConfigBean tableConfig = (TableConfigBean)tableConfigMap.get(table_type);
				System.out.println("@@@table_name==="+tableConfig.getName());
				System.out.println("@@@table_sql==="+tableConfig.getSql());
				System.out.println("@@@table_type==="+tableConfig.getType());
				System.out.println("@@@table_filter==="+tableConfig.getFilter());
				List<FieldConfigBean> fieldlist = tableConfig.getFieldList();
				Iterator<FieldConfigBean> it = fieldlist.iterator();
				while(it.hasNext()) {	
					FieldConfigBean fieldConfigBean = (FieldConfigBean)it.next();
					System.out.println("	@@@field_name==="+fieldConfigBean.getName());
					System.out.println("	@@@field_type==="+fieldConfigBean.getType());
					System.out.println("	@@@uap_field_name==="+fieldConfigBean.getUapField());
					System.out.println("	@@@uap_field_type==="+fieldConfigBean.getUapType());
					System.out.println("	@@@main_field==="+fieldConfigBean.getMainField());
					System.out.println("	@@@get_method==="+fieldConfigBean.getGet());
					System.out.println("	@@@set_method==="+fieldConfigBean.getSet());
					System.out.println("	@@@attr_id==="+fieldConfigBean.getAttrId());
				}
			}
			
			PermissionConfigBean permissionConfigBean = LoadAppResConfig.getAppConfigMap().get(appCode).getPermissionConfigBean();
			if(permissionConfigBean != null)
			{
				System.out.println("	@@@permission_type==="+permissionConfigBean.getType());
				System.out.println("	@@@permission_roottype==="+permissionConfigBean.getRoottype());
				System.out.println("	@@@permission_rootvalue==="+permissionConfigBean.getRootvalue());
			}
		}
	}

}
