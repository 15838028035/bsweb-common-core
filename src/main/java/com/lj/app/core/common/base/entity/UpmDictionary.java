package com.lj.app.core.common.base.entity;

/**
 * 
 * 数据字典
 *
 */
public class UpmDictionary extends BaseEntity {

  /**
   * 编号 id
   */
  private Integer id;
  /**
   * 类别编码 type_code
   */
  private String typeCode;

  /**
   * 数据编码 data_code
   */
  private String dataCode;

  /**
   * 描述 data_desc
   */
  private String dataDesc;

  /**
   * 排序 sort_no
   */
  private Integer sortNo;

  /**
   * 节点ID node_id
   */
  private Integer nodeId;

  /**
   * 应用ID app_id
   */
  private String appId;

  public java.lang.Integer getId() {
    return id;
  }

  public void setId(java.lang.Integer id) {
    this.id = id;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getDataCode() {
    return dataCode;
  }

  public void setDataCode(String dataCode) {
    this.dataCode = dataCode;
  }

  public String getDataDesc() {
    return dataDesc;
  }

  public void setDataDesc(String dataDesc) {
    this.dataDesc = dataDesc;
  }

  public java.lang.Integer getSortNo() {
    return sortNo;
  }

  public void setSortNo(java.lang.Integer sortNo) {
    this.sortNo = sortNo;
  }

  public java.lang.Integer getNodeId() {
    return nodeId;
  }

  public void setNodeId(java.lang.Integer nodeId) {
    this.nodeId = nodeId;
  }

}
