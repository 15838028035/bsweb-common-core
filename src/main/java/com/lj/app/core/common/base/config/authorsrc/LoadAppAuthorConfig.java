package com.lj.app.core.common.base.config.authorsrc;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;

import com.lj.app.core.common.util.StringUtil;


/**
 * 
 * <p>
 * 装载应用资源的权限配置信息
 */
@SuppressWarnings("unchecked")
public class LoadAppAuthorConfig {

	/**
	 * <p>
	 * 每个应用对应一个配置文件，对应Map中的一条记录。以AppCode值作为key值
	 * </p>
	 */
	private static Map<String, AppAuthorConfigBean> appAuthorConfigMap = new HashMap<String, AppAuthorConfigBean>();
	private static final String CONFIG_FILE_PATH_PREFIX = "config/appconfig/authorsrc/";
	private static Log logger = LogFactory.getLog(LoadAppAuthorConfig.class);
	private static final String DEFAULT_BATCH = "N";
	static {
		reload();
	}

	/**
	 * <p>
	 * 重新加载应用资源配置信息
	 * </p>
	 * 
	 */
	public static void reload() {
		appAuthorConfigMap.clear();
		loadCfgFileInClassPath(CONFIG_FILE_PATH_PREFIX);
	}

	/**
	 * <p>
	 * 加载所有应用资源配置文件
	 * </p>
	 * 
	 */
	private static void loadCfgFileInClassPath(String filePath) {
		logger.debug("filePath====" + filePath);
		if (filePath == null) {
			return;
		}
		AppAuthorConfigBean appConfigItem = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			URL fileUrl = null;
			try {
				fileUrl = ResourceUtils.getURL("classpath:" + filePath);
			} catch (Exception e) {
				logger.info("filePath[" + filePath + "] is not exist");
			}
			if (fileUrl != null && ResourceUtils.isJarURL(fileUrl)) {
				logger.debug("--------this is a jar file!");
				String filepath = ResourceUtils.extractJarFileURL(fileUrl).toString();
				logger.debug("------filepath-------" + filepath);
				filepath = filepath.substring("file:".length());
				logger.debug("filepath2====" + filepath);
				JarFile jarFile = new JarFile(filepath);
				Enumeration<JarEntry> entrys = jarFile.entries();
				while (entrys.hasMoreElements()) {
					JarEntry jar = entrys.nextElement();
					String path = jar.getName();
					// path.substring(filePath.length()).indexOf("\/") == -1
					// jar包文件遍历，只在本层目录下查找，不包含子目录
					if (path.startsWith(filePath) && path.substring(filePath.length()).indexOf("/") == -1
							&& path.endsWith(".xml")) {
						logger.debug("now deal xml-file in jar:" + jar.getName());
						String appcode = null;
						InputStream is = jarFile.getInputStream(jar);
						if (is != null) {
							Document doc = builder.build(is);
							Element root = doc.getRootElement();
							appcode = root.getAttributeValue("appcode");
							// 解析单个文件
							appConfigItem = parseAppAuthorConfig(root);
							appAuthorConfigMap.put(appcode, appConfigItem);
						}
					}
				}
			} else {
				File cfgFilePath = null;
				try {
					cfgFilePath = ResourceUtils.getFile("classpath:" + filePath);
				} catch (Exception e) {
					logger.info("cfgFilePath[" + cfgFilePath + "] is not exist");
				}
				logger.debug("cfgFilePath===" + cfgFilePath);
				if (cfgFilePath != null && cfgFilePath.exists()) {
					File[] cfgFiles = cfgFilePath.listFiles();// 得到目录下的所有的配置文件
					for (int i = 0; i < cfgFiles.length; i++) {
						String appcode = null;
						String fileName = cfgFiles[i].getName();
						if (cfgFiles[i].isFile() && fileName.endsWith("xml")) {
							logger.debug("now deal with file===" + fileName);
							Document doc = builder.build(cfgFiles[i].toURL());
							Element root = doc.getRootElement();
							appcode = root.getAttributeValue("appcode");
							// 解析单个文件
							appConfigItem = parseAppAuthorConfig(root);
							appAuthorConfigMap.put(appcode, appConfigItem);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 装载单个应用权限配置文件
	 * 
	 * @param Element
	 * @return AppAuthorConfigBean,返回权限配置对象
	 */
	private static AppAuthorConfigBean parseAppAuthorConfig(Element root) throws Exception {

		AppAuthorConfigBean appAuthorConfigBean = new AppAuthorConfigBean();

		// 加载用户授权
		Element userGrantRoot = root.getChild("usergrants");
		if (userGrantRoot != null) {
			AppUserGrantConfigBean appUserGrantConfigBean = parseUserGrants(userGrantRoot);
			appAuthorConfigBean.setAppUserGrantConfigBean(appUserGrantConfigBean);

		}

		// 加载各种操作对象
		Element options = root.getChild("options");
		if (options != null) {
			List<Element> optionList = options.getChildren("option");
			if (optionList != null) {
				List<AppAuthorOption> list = parseOption(optionList);
				appAuthorConfigBean.setOptions(list);
			}
		}

		return appAuthorConfigBean;
	}

	/**
	 * 加载用户授权
	 * 
	 * @param userGrantRoot
	 * @return
	 */
	private static AppUserGrantConfigBean parseUserGrants(Element userGrantRoot) {

		AppUserGrantConfigBean appUserGrantConfigBean = new AppUserGrantConfigBean();

		// 用户授权操作是tab-一个按钮进入同一页面多tab，还是button-各自按钮形式
		appUserGrantConfigBean.setType(userGrantRoot.getAttributeValue("type"));
		// type=tab时，总按钮的权限控制编码
		appUserGrantConfigBean.setCode(userGrantRoot.getAttributeValue("code"));
		// type=tab时，总按钮的按钮描述
		appUserGrantConfigBean.setName(userGrantRoot.getAttributeValue("name"));
		// type=tab时，按钮点击后跳转的页面
		appUserGrantConfigBean.setUrl(userGrantRoot.getAttributeValue("url"));
		// type=tab时，按钮跳转后的页面的类型：0-嵌套页面；1-非嵌套页面
		appUserGrantConfigBean.setUrltype(userGrantRoot.getAttributeValue("urltype"));
		// type=tab时，是否支持多选
		appUserGrantConfigBean
				.setIsBatch(StringUtil.isNull(userGrantRoot.getAttributeValue("isBatch")) ? DEFAULT_BATCH
						: userGrantRoot.getAttributeValue("isBatch"));
		// 用户授权各对象
		List<Element> userGrants = userGrantRoot.getChildren("usergrant");
		if (userGrants != null) {
			List<AppUserGrantBean> appUserGrants = parseUserGrant(userGrants);
			appUserGrantConfigBean.setAppUserGrants(appUserGrants);
		}
		
		// 用户扩展按钮
		List<Element> extButtons = userGrantRoot.getChildren("extbutton");
		if (extButtons != null) {
			List<AppUserExtButton> appUserExtButtons = parseUserExtButton(extButtons);
			appUserGrantConfigBean.setAppUserExtButtons(appUserExtButtons);
		}
		
		return appUserGrantConfigBean;
	}

	private static List<AppUserGrantBean> parseUserGrant(List<Element> userGrants) {
		List<AppUserGrantBean> appUserGrants = new ArrayList<AppUserGrantBean>();
		for (Element userGrant : userGrants) {
			AppUserGrantBean appUserGrantBean = new AppUserGrantBean();
			// 此授权是否属于反向关系
			appUserGrantBean.setIsReverst(userGrant.getAttributeValue("is_rev"));
			// 此授权对象是否是用户的属性，如果是，在optype中维护
			appUserGrantBean.setIsAttr(userGrant.getAttributeValue("is_attr"));
			// 授权对象的页面或按钮的权限控制
			appUserGrantBean.setCode(userGrant.getAttributeValue("code"));
			// 授权对象的操作对象
			appUserGrantBean.setOptype(userGrant.getAttributeValue("optype"));
			// 授权对象的页面title或按钮描述
			appUserGrantBean.setName(userGrant.getAttributeValue("name"));
			// 授权对象的页面url
			appUserGrantBean.setUrl(userGrant.getAttributeValue("url"));
			// 授权对象的页面url类型:0-嵌套页面；1-非嵌套页面
			appUserGrantBean.setUrltype(userGrant.getAttributeValue("urltype"));
			// 是否可以批量赋予主授权对象
			appUserGrantBean
					.setIsBatch(StringUtil.isNull(userGrant.getAttributeValue("isBatch")) ? DEFAULT_BATCH
							: userGrant.getAttributeValue("isBatch"));
			// 批量excel导入时的描述
			appUserGrantBean.setBatchDesc(userGrant.getAttributeValue("batchDesc"));
			appUserGrantBean.setBatchTemplatePath(userGrant.getAttributeValue("batchTemplatePath"));
			appUserGrants.add(appUserGrantBean);
		}
		return appUserGrants;
	}
	
	private static List<AppUserExtButton> parseUserExtButton(List<Element> extButtons) {
		List<AppUserExtButton> appUserExtButtons = new ArrayList<AppUserExtButton>();
		for (Element extButton : extButtons) {
			AppUserExtButton appUserExtButton = new AppUserExtButton();
			// 扩展按钮的权限控制
			appUserExtButton.setCode(extButton.getAttributeValue("code"));
			// 扩展按钮的按钮描述
			appUserExtButton.setName(extButton.getAttributeValue("name"));
			// 扩展按钮的rl
			appUserExtButton.setUrl(extButton.getAttributeValue("url"));
			// 扩展按钮的关键字key
			appUserExtButton.setKey(extButton.getAttributeValue("key"));
			// 扩展按钮的多选控制
			appUserExtButton.setMulticount(extButton.getAttributeValue("multicount"));
			// 扩展按钮的点击验证
			appUserExtButton.setBeforeopen(extButton.getAttributeValue("beforeopen"));
			appUserExtButtons.add(appUserExtButton);
		}
		return appUserExtButtons;
	}

	private static List<AppAuthorOption> parseOption(List<Element> optionList) {
		List<AppAuthorOption> list = new ArrayList<AppAuthorOption>();
		for (Element option : optionList) {
			AppAuthorOption appAuthorOption = new AppAuthorOption();

			appAuthorOption.setCode(option.getAttributeValue("code"));
			appAuthorOption.setType(option.getAttributeValue("type"));
			appAuthorOption.setName(option.getAttributeValue("name"));
			appAuthorOption.setUrl(option.getAttributeValue("url"));
			appAuthorOption.setUrltype(option.getAttributeValue("urltype"));
			appAuthorOption.setIsBatch(StringUtil.isNull(option.getAttributeValue("isBatch")) ? DEFAULT_BATCH
					: option.getAttributeValue("isBatch"));

			Element operateRoot = option.getChild("operates");
			if (operateRoot != null) {
				List<Element> appAuthorOptionOperateElements = operateRoot.getChildren("operate");
				if (appAuthorOptionOperateElements != null) {
					List<AppAuthorOptionOperate> appAuthorOptionOperates = parseOptionOperate(appAuthorOptionOperateElements);
					appAuthorOption.setOperateList(appAuthorOptionOperates);
				}
			}

			Element optiongrantsRoot = option.getChild("optiongrants");
			if (optiongrantsRoot != null) {

				AppAuthorOptionGrantConfigBean appAuthorOptionGrantConfigBean = parseOptionGrants(optiongrantsRoot);
				appAuthorOption.setAppAuthorOptionGrantConfigBean(appAuthorOptionGrantConfigBean);
			}

			list.add(appAuthorOption);
		}
		return list;
	}

	private static AppAuthorOptionGrantConfigBean parseOptionGrants(Element optiongrantsRoot) {
		AppAuthorOptionGrantConfigBean appAuthorOptionGrantConfigBean = new AppAuthorOptionGrantConfigBean();
		appAuthorOptionGrantConfigBean.setCode(optiongrantsRoot.getAttributeValue("code"));
		appAuthorOptionGrantConfigBean.setType(optiongrantsRoot.getAttributeValue("type"));
		appAuthorOptionGrantConfigBean.setName(optiongrantsRoot.getAttributeValue("name"));
		appAuthorOptionGrantConfigBean.setUrl(optiongrantsRoot.getAttributeValue("url"));
		appAuthorOptionGrantConfigBean.setUrltype(optiongrantsRoot.getAttributeValue("urltype"));
		appAuthorOptionGrantConfigBean.setIsBatch(StringUtil.isNull(optiongrantsRoot
				.getAttributeValue("isBatch")) ? DEFAULT_BATCH : optiongrantsRoot.getAttributeValue("isBatch"));

		List<Element> appAuthorOptionGrantElements = optiongrantsRoot.getChildren("optiongrant");
		if (appAuthorOptionGrantElements != null) {
			List<AppAuthorOptionGrant> appAuthorOptionGrants = parseOptionGrant(appAuthorOptionGrantElements);
			appAuthorOptionGrantConfigBean.setAppAuthorOptionGrants(appAuthorOptionGrants);
		}
		return appAuthorOptionGrantConfigBean;
	}

	private static List<AppAuthorOptionGrant> parseOptionGrant(List<Element> appAuthorOptionGrantElements) {
		List<AppAuthorOptionGrant> appAuthorOptionGrants = new ArrayList<AppAuthorOptionGrant>();
		for (Element appAuthorOptionGrantElement : appAuthorOptionGrantElements) {
			AppAuthorOptionGrant appAuthorOptionGrant = new AppAuthorOptionGrant();
			appAuthorOptionGrant.setCode(appAuthorOptionGrantElement.getAttributeValue("code"));
			appAuthorOptionGrant.setOptype(appAuthorOptionGrantElement.getAttributeValue("optype"));
			appAuthorOptionGrant.setName(appAuthorOptionGrantElement.getAttributeValue("name"));
			appAuthorOptionGrant.setUrl(appAuthorOptionGrantElement.getAttributeValue("url"));
			appAuthorOptionGrant.setUrltype(appAuthorOptionGrantElement.getAttributeValue("urltype"));
			appAuthorOptionGrant.setIsAttr(appAuthorOptionGrantElement.getAttributeValue("is_attr"));
			appAuthorOptionGrant.setIsReverst(appAuthorOptionGrantElement.getAttributeValue("is_rev"));
			appAuthorOptionGrant.setIsBatch(StringUtil.isNull(appAuthorOptionGrantElement
					.getAttributeValue("isBatch")) ? DEFAULT_BATCH : appAuthorOptionGrantElement
					.getAttributeValue("isBatch"));
			appAuthorOptionGrant.setBatchDesc(appAuthorOptionGrantElement.getAttributeValue("batchDesc"));
			appAuthorOptionGrant.setBatchTemplatePath(appAuthorOptionGrantElement.getAttributeValue("batchTemplatePath"));
			appAuthorOptionGrants.add(appAuthorOptionGrant);
		}
		return appAuthorOptionGrants;
	}

	private static List<AppAuthorOptionOperate> parseOptionOperate(List<Element> appAuthorOptionOperateElements) {
		List<AppAuthorOptionOperate> appAuthorOptionOperates = new ArrayList<AppAuthorOptionOperate>();
		for (Element appAuthorOptionOperateElement : appAuthorOptionOperateElements) {
			AppAuthorOptionOperate appAuthorOptionOperate = new AppAuthorOptionOperate();
			appAuthorOptionOperate.setCode(appAuthorOptionOperateElement.getAttributeValue("code"));
			appAuthorOptionOperate.setName(appAuthorOptionOperateElement.getAttributeValue("name"));
			appAuthorOptionOperate.setUrl(appAuthorOptionOperateElement.getAttributeValue("url"));
			appAuthorOptionOperate.setUrltype(appAuthorOptionOperateElement.getAttributeValue("urltype"));
			appAuthorOptionOperate.setType(appAuthorOptionOperateElement.getAttributeValue("type"));
			appAuthorOptionOperate.setIsBatch(StringUtil.isNull(appAuthorOptionOperateElement
					.getAttributeValue("isBatch")) ? DEFAULT_BATCH : appAuthorOptionOperateElement
					.getAttributeValue("isBatch"));
			appAuthorOptionOperate.setBatchDesc(appAuthorOptionOperateElement.getAttributeValue("batchDesc"));
			appAuthorOptionOperate.setBatchTemplatePath(appAuthorOptionOperateElement.getAttributeValue("batchTemplatePath"));
			appAuthorOptionOperates.add(appAuthorOptionOperate);
		}
		return appAuthorOptionOperates;
	}

	/**
	 * <p>
	 * main 测试函数
	 * </p>
	 */
	public static void main(String[] args) {
		Set<String> appConfigSet = appAuthorConfigMap.keySet();
		Iterator<String> itSrc = appConfigSet.iterator();
		while (itSrc.hasNext()) {
			String appCode = (String) itSrc.next();
			System.out.println("###################appCode################=====" + appCode);

			AppAuthorConfigBean appAuthorConfigBean = appAuthorConfigMap.get(appCode);

			System.out.println("=====user_grants=====");
			AppUserGrantConfigBean appUserGrantConfigBean = appAuthorConfigBean.getAppUserGrantConfigBean();
			System.out.println("\t[code=" + appUserGrantConfigBean.getCode() + "]");
			System.out.println("\t[name=" + appUserGrantConfigBean.getName() + "]");
			System.out.println("\t[type=" + appUserGrantConfigBean.getType() + "]");
			System.out.println("\t[url=" + appUserGrantConfigBean.getUrl() + "]");
			System.out.println("\t[urltype=" + appUserGrantConfigBean.getUrltype() + "]");
			List<AppUserGrantBean> AppUserGrantBeans = appUserGrantConfigBean.getAppUserGrants();
			for (AppUserGrantBean appUserGrantBean : AppUserGrantBeans) {
				String isRev = appUserGrantBean.getIsReverst();
				System.out.println("\t\tuser_grant[" + appUserGrantBean.getName() + "]"
						+ (StringUtil.isEqualsY(isRev) ? "(reverse acct author relation)" : "") + ":");
				String isAttr = appUserGrantBean.getIsAttr();
				if (isAttr == null || !isAttr.equalsIgnoreCase("Y")) {
					System.out.println("\t\t\tcode=" + appUserGrantBean.getCode());
					System.out.println("\t\t\turl=" + appUserGrantBean.getUrl());
					System.out.println("\t\t\turltype=" + appUserGrantBean.getUrltype());
				} else {
					System.out.println("\t\t\tisAttr=" + isAttr);
				}
				System.out.println("\t\t\toptype=" + appUserGrantBean.getOptype());
			}

			System.out.println("=====options=====");
			List<AppAuthorOption> lists = appAuthorConfigBean.getOptions();
			for (AppAuthorOption appAuthorOption : lists) {
				System.out.println("\toption[" + appAuthorOption.getName() + "]:");
				System.out.println("\t\tcode=" + appAuthorOption.getCode());
				System.out.println("\t\turl=" + appAuthorOption.getUrl());
				System.out.println("\t\turltype=" + appAuthorOption.getUrltype());
				List<AppAuthorOptionOperate> list = appAuthorOption.getOperateList();
				if (list != null && list.size() > 0) {
					System.out.println("\t\toption_operate[size=" + list.size() + "]");
					for (AppAuthorOptionOperate appAuthorOptionOperate : list) {
						System.out.print("\t\t\t[" + appAuthorOptionOperate.getName());
						System.out.print("(" + appAuthorOptionOperate.getType() + ")");
						System.out.print("]");
						System.out.print("(");
						System.out.print("code=" + appAuthorOptionOperate.getCode() + ",");
						System.out.print("urltype=" + appAuthorOptionOperate.getUrltype() + ",");
						System.out.print("url=" + appAuthorOptionOperate.getUrl());

						System.out.println(")");
					}
				}
				AppAuthorOptionGrantConfigBean appAuthorOptionGrantConfigBean = appAuthorOption
						.getAppAuthorOptionGrantConfigBean();
				if (appAuthorOptionGrantConfigBean != null) {
					System.out.print("\t\toption_grants[" + appAuthorOptionGrantConfigBean.getName());
					System.out.print("](");
					System.out.print("code=" + appAuthorOptionGrantConfigBean.getCode());
					System.out.print(",");
					System.out.print("type=" + appAuthorOptionGrantConfigBean.getType());
					System.out.print(",");
					System.out.print("url=" + appAuthorOptionGrantConfigBean.getUrl());
					System.out.print(",");
					System.out.print("urltype=" + appAuthorOptionGrantConfigBean.getUrltype());
					System.out.println(")");
					List<AppAuthorOptionGrant> appAuthorOptionGrants = appAuthorOptionGrantConfigBean
							.getAppAuthorOptionGrants();
					for (AppAuthorOptionGrant appAuthorOptionGrant : appAuthorOptionGrants) {
						String isRev = appAuthorOptionGrant.getIsReverst();
						System.out.println("\t\t\toption_grant[" + appAuthorOptionGrant.getName() + "]"
								+ (StringUtil.isEqualsY(isRev) ? "(reverse entity author relation)" : ""));
						String isAttr = appAuthorOptionGrant.getIsAttr();
						if (isAttr == null || !isAttr.equalsIgnoreCase("Y")) {
							System.out.println("\t\t\t\tcode=" + appAuthorOptionGrant.getCode());
							System.out.println("\t\t\t\turl=" + appAuthorOptionGrant.getUrl());
							System.out.println("\t\t\t\turltype=" + appAuthorOptionGrant.getUrltype());
						} else {
							System.out.println("\t\t\t\tisAttr=" + isAttr);
						}
						System.out.println("\t\t\t\toptype=" + appAuthorOptionGrant.getOptype());
					}
				}
			}
		}
	}

	public static AppAuthorConfigBean getAppAuthorConfig(String appCode) {
		//reload();
		return appAuthorConfigMap.get(appCode);
	}
}
