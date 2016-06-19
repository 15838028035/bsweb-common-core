package com.lj.app.core.common.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.security.CMSecurityContext;
import com.lj.app.core.common.util.StringUtil;

public  class UpmPermissionActionApi {

	public static final String FIND_PERMISSION_BY_USERID_URL = PropertiesUtil.getPropertyTrim("FIND_PERMISSION_BY_USERID_URL");
	
	
	public static CMSecurityContext getSecurityContext(int userId, String contextPath, String appId) {
		CMSecurityContext securityContext = new CMSecurityContext();
		
		//设置主帐号信息
		securityContext.setMainAcctId(Long.parseLong(String.valueOf(userId)));
		
		CMSecurityContext mainAcct;

		//设置权限相关的code和url
		Set<String> codeSet = new HashSet<String>();
		Set<String> urlSet = new HashSet<String>();
		URLConnection connection = null;
        try {
        	String postUrl = FIND_PERMISSION_BY_USERID_URL.replace("${userId}", String.valueOf(userId)).replace("${appId}", appId);
            connection = new URL(postUrl).openConnection();
            connection.connect();
            InputStream fin = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fin,"UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
            JSONArray jsonArray = JSONArray.fromObject(buffer.toString());
            
            for(int j=0; j<jsonArray.size();j++){
            	 JSONObject obj = jsonArray.getJSONObject(j);
		            int id =  (Integer)obj.get("id");
		            String code =  (String)obj.get("code");
		            String url =  (String)obj.get("url");
		            
		          //设置菜单，按钮对应的code
					codeSet.add(code);
					
					if(StringUtil.isNotBlank(url)) {
						if(url.indexOf(",") != -1) {
							String[] splitUrls = url.split(",");
							for (int i = 0; i < splitUrls.length; i++) {
								urlSet.add(contextPath + splitUrls[i]);
							}
						} else {
							urlSet.add(contextPath + url);
						}
					}
					
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		securityContext.setUrls(urlSet);
		securityContext.setCodes(codeSet);
		return securityContext;
	}
}