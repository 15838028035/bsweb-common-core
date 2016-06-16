package com.lj.app.core.common.appsso.service;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.lj.app.core.common.appsso.model.UpmAppSsoUrl;
import com.lj.app.core.common.base.api.CreateTokenApiService;
import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.util.SessionCode;
import com.lj.app.core.common.util.SessionUtil;
import com.lj.app.core.common.web.Struts2Utils;
import com.opensymphony.xwork2.util.Element;

/**
 * @title :AppResServiceImpl.java
 */
@Service
public class AppResServiceImpl implements IAppResService {
	protected static Logger logger = Logger.getLogger(AppResServiceImpl.class);
    @Autowired
    CreateTokenApiService createTokenService;

	public String createSoapReturn(String isSuccess,String Remark) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<ROOT>");
		   sb.append("<LOG4A>");
		      sb.append("<RESULT> "+isSuccess+"</RESULT>");
		      sb.append("<REMARK>"+Remark+"</REMARK>");
		   sb.append("</LOG4A>");
		sb.append("</ROOT>");
		return sb.toString();
	}
	
	private Document build(ByteArrayInputStream is){
		/*SAXBuilder builder = new SAXBuilder(false);
		Document doc = null ;
		try {
			doc = builder.build(is);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc ;*/
		return null;
	}
    
	private Element getDomBody(String message) throws Exception {
		/*SAXBuilder builder = new SAXBuilder(false);
		ByteArrayInputStream is = new ByteArrayInputStream(message
				.getBytes("UTF-8"));
		Document doc = null;
		doc = builder.build(is);
		Element root = doc.getRootElement();
		return root;*/
		return null;
	}
	
	/**
	 * @description: 单点登录系统UPM
	 * @return:String
	 */
	public String accessUpmSysSSO(){
		String accessUpmSysSSOUrl = "/loginAction!ssoLogin.action";
		// 生成单点登录参数
		String url = "";
		String appCode = "";
		String acctSeq = String.valueOf(SessionUtil.getMainAcctId());
		String appAcctId = String.valueOf(SessionUtil.getMainAcctId());
		String appAcctName = String.valueOf(SessionUtil.getMainAcctId());
		String appId = "";
		
		url = PropertiesUtil.getProperty("accessUpmSysSSO");
		appCode ="UPM";

		String newURL = null;

		// 获取客户端信息
		String ipAddress = Struts2Utils.getSession().getAttribute(SessionCode.CLIENT_INFO_IP_ADDRESS) != null ? Struts2Utils.getSession()
				.getAttribute(SessionCode.CLIENT_INFO_IP_ADDRESS).toString() : "";
		String macAddress = Struts2Utils.getSession().getAttribute(SessionCode.CLIENT_INFO_MAC_ADDRESS) != null ? Struts2Utils.getSession()
				.getAttribute(SessionCode.CLIENT_INFO_MAC_ADDRESS).toString() : "";
		String cpuSerial = Struts2Utils.getSession().getAttribute(SessionCode.CLIENT_INFO_CPU_SERIAL) != null ? Struts2Utils.getSession()
				.getAttribute(SessionCode.CLIENT_INFO_CPU_SERIAL).toString() : "";
		String hostName = Struts2Utils.getSession().getAttribute(SessionCode.CLIENT_INFO_HOST_NAME) != null ? Struts2Utils.getSession()
				.getAttribute(SessionCode.CLIENT_INFO_HOST_NAME).toString() : "";
		String hostAccount = Struts2Utils.getSession().getAttribute(SessionCode.CLIENT_INFO_HOST_ACCOUNT) != null ? Struts2Utils.getSession()
				.getAttribute(SessionCode.CLIENT_INFO_HOST_ACCOUNT).toString() : "";
		int mainAcctId = new BigDecimal(SessionUtil.getMainAcctId()).intValue();
		
		try {
			// 单点登录URL
			newURL = getSSOAppUrl(mainAcctId, url, acctSeq, appAcctId, appAcctName, appId, appCode, ipAddress, macAddress, cpuSerial,
					hostName, hostAccount);
		} catch (Exception e) {
			logger.error(e);
		}
		accessUpmSysSSOUrl = newURL;
		
		Struts2Utils.getSession().setAttribute(SessionCode.SSO_LOGIN_URL,accessUpmSysSSOUrl);
		return accessUpmSysSSOUrl;
	}
	
	public String getSSOAppUrl(int mainAcctId,String url, String acctSeq , String appAcctId,String appAcctName ,String appId,String appCode,String ipAddress,String macAddress,String cpuSerial,String hostName,String hostAccount){
		return getSSOAppUrl(mainAcctId, url, acctSeq, appAcctId, appAcctName, appId, appCode, ipAddress, macAddress, cpuSerial, hostName, hostAccount, "", "");
	}
    /**
     * @description: 获取登录应用的url
     * @param mainAcctId
     * @param url
     * @param acctSeq
     * @param appAcctId
     * @param appAcctName
     * @param appId
     * @param appCode
     * @param ipAddress
     * @param macAddress
     * @param cpuSerial
     * @param hostName
     * @param hostAccount
     * @return＄1�7
     */
    public String getSSOAppUrl(int mainAcctId,String url, String acctSeq , String appAcctId,String appAcctName ,String appId,String appCode,String ipAddress,String macAddress,String cpuSerial,String hostName,String hostAccount,
    		String bankApprove,String bankFlag){
        
        String token="";
        //获取token
        try {
            token = createTokenService.CreateToken(acctSeq, appId, mainAcctId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String startTag = url.indexOf("?")!=-1 ? "&":"?";
        String bakFlag="1";
        String businessAddress = "";
        
    	url += startTag+"appAcctId=" + acctSeq + "&token=" + token+"&flag="+bakFlag+"&ipAddress="+ipAddress+"&macAddress="+macAddress+"&cpuSerial="+cpuSerial+"&hostName="+hostName+"&hostAccount="+hostAccount
    	+"&mac="+macAddress+"&ip="+ipAddress+"&client="+hostName+"&username="+hostAccount+"&businessAddress="+businessAddress;
        return url;
    }

	/**
	 * 获得ssoUrl列表
	 * @param appCode 编码
	 * @param appId  应用id
	 * @param loginAcct 登录id
	 * @return
	 */
	public List<UpmAppSsoUrl> getSsoUrlList(String appCode, String appId, String loginAcct) {
		return null;
	}
	
	/**
	 * 根据appCode获得子应用名称
	 * @param appCode
	 * @param subAppCode
	 * @return
	 */
	public String getAppName(String appCode, String subAppCode) {
		return null;
	}
}
