package com.lj.app.core.common.base.service;

import java.util.List;

public interface UpmOrganizationService<UpmOrganization> extends BaseService {
  /**
   * save org.in this,will insert mng permission and audit permission.
   * 
   * @param UpmOrganization
   * @param createMainacct
   * @param parentOrgid
   * @return
   */
  public UpmOrganization saveServiceOrg(UpmOrganization UpmOrganization, Long createMainacct, Long parentOrgid)
      throws Exception;

  /**
   * 根据父结点查找子结点列表
   * 
   * @param treeNodeId
   * @return
   */
  public List<UpmOrganization> findOrgByParentId(Integer treeNodeId);

  /**
   * 根据父结点查找子结点列表，并且带有组织机构级别
   * 
   * @param treeNodeId
   * @return
   */
  public List<UpmOrganization> findOrgByOrgParentId(Integer treeNodeId);

  /**
   * 查找2个节点拥有的三级共同父节点
   * 
   * @param treeNodeId
   * @return
   */
  public List<UpmOrganization> findSameNodeById(String oldOrgId, String orgId);

  /**
   * 根据名称查找子结点列表
   * 
   * @param treeNodeId
   * @return
   */
  public List<UpmOrganization> findOrgByOrgName(Integer treeNodeId, String orgName);

  public List findUpmOrganizationByName(String organizationName, Long organizationId);

  public boolean hasChildOrg(Integer orgId);

  /**
   * 查找一个组织机构的全路径
   * 
   * @param orgId
   * @return
   */
  public List getOrgPath(Integer orgId);

  public List<UpmOrganization> findChilderOrgs(Integer treeNodeId);

  /**
   * 根据组织名字和父组织ID查询组织列表
   * 
   * @param organizationName
   * @param organizationId
   * @return 组织列表
   */
  public List<UpmOrganization> findUapOrgByNameAndParentId(String organizationName, Long organizationId);

  /**
   * query the list of orgnizations ,all were params orgid's parent.<br />
   * include itself.
   * 
   * @param orgId
   * @return
   */
  public List<UpmOrganization> getParentOrgs(Integer orgId);

  /**
   * query the list of orgnizations ,all were params orgid's children.<br />
   * exclude itself.
   * 
   * @param orgId
   * @return
   */
  public List<UpmOrganization> getChildrenOrgs(Integer orgId);

  /**
   * 查询主帐号所在组织机构及其所有父组织机构
   * 
   * @param mainAcctId
   * @return
   */
  public String findSelfAndParentOrgTreeDataJson(Long mainAcctId) throws Exception;

  /**
   * 根据组织机构ID获取组织全路径
   * 
   * @param orgId
   *          组织机构ID
   * @return 全路径
   *
   */
  public String findOrgFullDir(Integer orgId) throws Exception;

  /**
   * 根据组织名称查询组织及其所有上层组织
   * 
   * @param belongOrg
   * @param param
   * @return
   */
  public List<UpmOrganization> findOrgListByName(Integer belongOrg, String param, String netflag);

  /**
   * 获取自然人组织树显示的根节点
   * 
   * @param mainAcctId
   *          主帐号id
   * @param isOut
   * @return 当前主帐号可管理的根组织id
   */
  public Integer getOrgRootId(Integer mainAcctId, boolean isOut);
}
