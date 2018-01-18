package com.lj.app.core.common.base.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lj.app.core.common.properties.PropertiesUtil;
import com.lj.app.core.common.util.StringUtil;

@Service("upmOrganizationService")
public class UpmOrganizationServiceImpl<UpmOrganization> extends BaseServiceImpl<UpmOrganization>
    implements UpmOrganizationService<UpmOrganization> {
  private Logger log = Logger.getLogger(UpmOrganizationServiceImpl.class);

  public UpmOrganization saveServiceOrg(UpmOrganization UpmOrganization, Integer createMainacct, Integer parentOrgid)
      throws Exception {
    // UpmOrganization uapParent = this.findOrganizationById(parentOrgid);
    // setParentOrg(uapParent);//FIXME
    insertObject(UpmOrganization);
    return UpmOrganization;
  }

  public List<UpmOrganization> findOrgByParentId(Integer treeNodeId) {
    /*
     * UpmOrganization o = new UpmOrganization(); UpmOrganization parent = new UpmOrganization();
     * parent.setOrgId(treeNodeId); o.setParentOrg(parent); List<UpmOrganization> results =
     * orgDao.findByExample(o,"sort_no,org_name");
     */
    // return results;
    // FIXME
    return null;
  }

  public List<UpmOrganization> findOrgByOrgName(Integer treeNodeId, String orgName) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("orgId", String.valueOf(treeNodeId));
    map.put("orgName", orgName);
    return queryForList("getChilderOrgsByName", map);

  }

  public List<UpmOrganization> findOrgByOrgParentId(Integer treeNodeId) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("orgId", String.valueOf(treeNodeId));
    return queryForList("getChilderOrgLevelByOrg", map);
  }

  public List<UpmOrganization> findChilderOrgs(long treeNodeId) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("orgId", String.valueOf(treeNodeId));
    return queryForList("getChilderOrgs", map);
  }

  public List<UpmOrganization> findUpmOrganizationByName(String organizationName, Long organizationId) {
    /*
     * UpmOrganization uapOrg = new UpmOrganization(); UpmOrganization uapOrgParent = new UpmOrganization();
     * uapOrgParent.setOrgId(organizationId); uapOrg.setParentOrg(uapOrgParent); uapOrg.setOrgName(organizationName);
     * return orgDao.findByExample2(uapOrg);
     */
    // FIXME
    return null;
  }

  public List<UpmOrganization> findUapOrgByNameAndParentId(String organizationName, Long organizationId) {
    return findUpmOrganizationByName(organizationName, organizationId);
  }

  public boolean hasChildOrg(Integer orgId) {
    /*
     * UpmOrganization uapOrg = new UpmOrganization(); UpmOrganization uapOrgParent = new UpmOrganization();
     * uapOrgParent.setOrgId(orgId); uapOrg.setParentOrg(uapOrgParent); List<UpmOrganization> list =
     * this.queryForList(uapOrg);
     * 
     * if (list != null && list.size() > 0) { return true; }
     * 
     * return false;
     */
    // FIXME
    return true;
  }

  public List getOrgPath(Integer orgId) {
    Map condition = new HashMap();
    condition.put("orgId", orgId);
    List listTemp = queryForList("getOrgPath", condition);
    if (listTemp != null) {
      Collections.reverse(listTemp);
    }
    return listTemp;
  }

  public List<UpmOrganization> findSameNodeById(String oldOrgId, String orgId) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("oldOrgId", oldOrgId);
    map.put("orgId", orgId);
    return queryForList("getSameNodeById", map);
  }

  public List<UpmOrganization> getParentOrgs(Integer orgId) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("orgId", String.valueOf(orgId));
    return queryForList("getOrgPath", map);
  }

  public List<UpmOrganization> getChildrenOrgs(Integer orgId) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("orgId", String.valueOf(orgId));
    return queryForList("getChilderOrgs", map);
  }

  public String findSelfAndParentOrgTreeDataJson(Long mainAcctId) throws Exception {
    // TODO Auto-generated method stub
    /*
     * List<UpmOrganization> orgs = orgDao.findSelfAndParentOrg(mainAcctId); if(null!=orgs && !orgs.isEmpty()){
     * List<DefaultTreeNodeNoCheck> treeNodeList = new ArrayList<DefaultTreeNodeNoCheck>(); for(int i=0; i<orgs.size();
     * i++){ UpmOrganization up = orgs.get(i); String id = up.getOrgId()+""; String text = up.getOrgName(); String
     * parentId = up.getParentOrg()==null?null:up.getParentOrg().getOrgId()+"";
     * treeNodeList.add(SimpleTreeNoCheck.createNew(id, text, parentId)); } SimpleTreeNoCheck simpleTree =
     * SimpleTreeNoCheck.valueOf(treeNodeList,"0"); if(null!=simpleTree){ return simpleTree.toJsonString(); } } return
     * null;
     */
    // FIXME
    return null;
  }

  public String findOrgFullDir(Integer orgId) throws Exception {
    String findOrgFullDirQuery = "findOrgFullDir";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("orgId", orgId);
    return (String) queryForObject(findOrgFullDirQuery, map);
  }

  /**
   * 根据组织名称查询组织及其所有上层组织
   * 
   * @param belongOrg
   * @param param
   * @return
   */
  public List<UpmOrganization> findOrgListByName(Integer belongOrg, String orgName) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("belongOrg", belongOrg.toString());
    map.put("orgName", orgName);
    return queryForList("findOrgListByName", map);
  }

  /**
   * 获取自然人组织树显示的根组织id
   * 
   * @param mainAcctId
   * 
   * @return 组织机构ID
   */
  public Integer getOrgRootId(Integer mainAcctId, boolean isOut) {

    Integer defaultRoot = 0;
    Integer outRootId = defaultRoot;

    String outRoot = PropertiesUtil.getProperty("outRootId");
    if (StringUtil.isNotNull(outRoot)) {
      try {
        outRootId = Integer.parseInt(outRoot.trim());
      } catch (Exception e) {
        log.error("config [outRootId] is wrong~");
      }
    }
    return outRootId;
  }

  @Override
  public UpmOrganization saveServiceOrg(UpmOrganization UpmOrganization, Long createMainacct, Long parentOrgid) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UpmOrganization> findChilderOrgs(Integer treeNodeId) {
    return null;
  }

  @Override
  public List<UpmOrganization> findOrgListByName(Integer belongOrg, String param, String netflag) {
    // TODO Auto-generated method stub
    return null;
  }

}
