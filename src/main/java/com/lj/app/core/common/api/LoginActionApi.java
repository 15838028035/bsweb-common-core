package com.lj.app.core.common.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.lj.app.core.common.properties.PropertiesUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 登陆Api
 *
 */
public class LoginActionApi {

  public static final String FIND_USER_INFO_URL = PropertiesUtil.getPropertyTrim("FIND_USER_INFO_URL");
  public static final String UPM_LOGIN_URL = PropertiesUtil.getPropertyTrim("UPM_LOGIN_URL");

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
      StringBuffer buffer = new StringBuffer();
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
      StringBuffer buffer = new StringBuffer();
      String temp = null;
      while ((temp = br.readLine()) != null) {
        buffer.append(temp);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}