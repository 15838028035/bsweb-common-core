package com.lj.app.core.common.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.lj.app.core.common.properties.PropertiesUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 登陆Api
 *
 */
public class LoginActionApi {

  public static final String FIND_USER_INFO_URL = PropertiesUtil.getPropertyTrim("FIND_USER_INFO_URL");
  public static final String UPM_LOGIN_URL = PropertiesUtil.getPropertyTrim("UPM_LOGIN_URL");
  public static final String ACCESS_UPM_SYSSSO = PropertiesUtil.getPropertyTrim("ACCESS_UPM_SYSSSO");
  public static final String FIND_PERMISSIONBY_UID = PropertiesUtil.getPropertyTrim("FIND_PERMISSIONBY_UID");
  

  /**
   * 获取登陆信息
   * 
   * @param userId
   *          用户名
   * @param pwd
   *          密码
   * @return 登陆用户信息
   */
  public static LoginUserInfo getLoginUserInfo(String userId, String pwd) {
    URLConnection connection = null;
    try {
      String postUrl = FIND_USER_INFO_URL.replace("${loginNo}", userId).replace("${pwd}", pwd);
      connection = new URL(postUrl).openConnection();
      connection.connect();

      InputStream fin = connection.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
      StringBuilder buffer = new StringBuilder();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }
      JSONObject obj = JSONObject.fromObject(buffer.toString());
      if (obj != null) {
        int id = (Integer) obj.get("id");
        String loginNo = (String) obj.get("loginNo");
        String userName = (String) obj.get("userName");
        String address = (String) obj.get("address");
        String mobile = (String) obj.get("mobile");
        String orgDesc = (String) obj.get("orgDesc");

        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setId(id);
        loginUserInfo.setLoginNo(loginNo);
        loginUserInfo.setUserName(userName);
        loginUserInfo.setAddress(address);
        loginUserInfo.setMobile(mobile);
        loginUserInfo.setOrgDesc(orgDesc);
        return loginUserInfo;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 登陆
   * 
   * @param userId
   *          用户名
   * @param pwd
   *          密码
   */
  public static void loginUpm(String userId, String pwd) {
    URLConnection connection = null;
    try {
      String postUrl = UPM_LOGIN_URL.replace("${loginNo}", userId).replace("${pwd}", pwd);
      connection = new URL(postUrl).openConnection();
      connection.connect();

      InputStream fin = connection.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
      StringBuilder buffer = new StringBuilder();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 登陆
   * 
   * @param userId
   *          用户名
   * @param pwd
   *          密码
   */
  public static void ssoLogin(String token) {
    URLConnection connection = null;
    try {
      String accessUpmSyssso = PropertiesUtil.getProperty("ACCESS_UPM_SYSSSO");
      String accessUpmSysssoURL  = accessUpmSyssso+ "&token="+token;
      
      String postUrl = accessUpmSysssoURL;
      connection = new URL(postUrl).openConnection();
      connection.connect();

      InputStream fin = connection.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
      StringBuilder buffer = new StringBuilder();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 获得权限信息
   * @param userId 用户ID
   * @param appId appID
   * @return
   */
  public static JSONArray  findPermissionByUserIdApi(String userId, String appId) {
    URLConnection connection = null;
    try {
      String postUrl = FIND_PERMISSIONBY_UID.replace("${userId}", userId).replace("${appId}", appId);
      connection = new URL(postUrl).openConnection();
      connection.connect();

      InputStream fin = connection.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
      StringBuilder buffer = new StringBuilder();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }
      JSONArray obj = JSONArray.fromObject(buffer.toString());
      
      return obj;

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
}