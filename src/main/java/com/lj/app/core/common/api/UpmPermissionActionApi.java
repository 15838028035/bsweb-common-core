package com.lj.app.core.common.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.security.CmSecurityContext;
import com.lj.app.core.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *权限信息APi
 *
 */
public class UpmPermissionActionApi {

  public static final String FIND_PERMISSION_BY_USERID_URL = PropertiesUtil
      .getPropertyTrim("FIND_PERMISSION_BY_USERID_URL");

  /**
   * 获得权限上下文信息
   * @param userId 用户ID
   * @param contextPath 上下文
   * @param appId 归属应用ID
   * @return 获得权限上下文信息
   */
  public static CmSecurityContext getSecurityContext(int userId, String contextPath, String appId) {
    CmSecurityContext securityContext = new CmSecurityContext();

    // 设置主帐号信息
    securityContext.setMainAcctId(Long.parseLong(String.valueOf(userId)));

    CmSecurityContext mainAcct;

    // 设置权限相关的code和url
    Set<String> codeSet = new HashSet<String>();
    Set<String> urlSet = new HashSet<String>();
    URLConnection connection = null;
    try {
      String postUrl = FIND_PERMISSION_BY_USERID_URL.replace("${userId}", String.valueOf(userId)).replace("${appId}",
          appId);
      connection = new URL(postUrl).openConnection();
      connection.connect();
      InputStream fin = connection.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
      StringBuilder buffer = new StringBuilder();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }
      JSONArray jsonArray = JSONArray.fromObject(buffer.toString());

      for (int j = 0; j < jsonArray.size(); j++) {
        JSONObject obj = jsonArray.getJSONObject(j);
        int id = (Integer) obj.get("id");
        String code = (String) obj.get("code");
        String url = (String) obj.get("url");

        // 设置菜单，按钮对应的code
        codeSet.add(code);

        if (StringUtil.isNotBlank(url)) {
          if (url.indexOf(",") != -1) {
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