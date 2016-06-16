package com.lj.app.core.common.base.config;

/**
 * 
 * <p>应用资源查询接口视图中字段的配置对象信息</p>
 */

public class FieldConfigBean {
	private String name;			//应用字段名称
	private String type;			//应用字段类型
	private String xmlTitle;		//对应XML报文的标签
	private String uapField;		//对应UAP字段名称
	private String uapType;			//对应UAP字段类型
	private String get;				//get方法
	private String set;				//set方法
	private String mainField;		//是否主表属性
	private String attrId;			//是否扩展表的属性标识
	private String mapKey;			//授权关系独有字段：一般指帐号
	private String lockValue;		//加锁状态字段特有属性。代表对方帐号加锁状态的值。在4A方帐号正常状态为"1",加锁状态为"0"
	private String unlockValue;
	private String resultColumn; //case when 返回结果字段名
	
	public String getResultColumn() {
		return resultColumn;
	}

	public void setResultColumn(String resultColumn) {
		this.resultColumn = resultColumn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setXmlTitle(String xmlTitle) {
		this.xmlTitle = xmlTitle;
	}
	
	public String getXmlTitle() {
		return xmlTitle;
	}
	
	public String getUapField() {
		return uapField;
	}

	public void setUapField(String uapField) {
		this.uapField = uapField;
	}

	public String getUapType() {
		return uapType;
	}

	public void setUapType(String uapType) {
		this.uapType = uapType;
	}

	public String getGet() {
		return get;
	}

	public void setGet(String get) {
		this.get = get;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}
	
	public String getMainField() {
		return mainField;
	}

	public void setMainField(String mainField) {
		this.mainField = mainField;
	}
	
	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	
	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	
	public String getLockValue() {
		return lockValue;
	}

	public void setLockValue(String lockValue) {
		this.lockValue = lockValue;
	}
	
	public String getUnlockValue() {
		return unlockValue;
	}

	public void setUnlockValue(String unlockValue) {
		this.unlockValue = unlockValue;
	}
}
