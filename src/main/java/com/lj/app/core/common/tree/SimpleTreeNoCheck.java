package com.lj.app.core.common.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 
 * 简单树
 *
 */
public class SimpleTreeNoCheck {
  private TreeNodeNoCheck rootNode;
  private boolean flag;// true:已处理过；false:未处理过

  /**
   * 唯一构造函数
   * 
   * @param rootId
   *          根节点
   * @param rootText
   *          根节点文本
   * 
   */
  public SimpleTreeNoCheck(String rootId, String rootText) {
    rootNode = new TreeNodeNoCheck();
    rootNode.setId(rootId);
    rootNode.setText(rootText);
    flag = false;
  }

  /**
   * 增加节点，默认父节点为根节点
   * 
   * @param id
   *          节点ID
   * @param text
   *          节点文本
   * @return TreeNodeNoCheck
   */
  public TreeNodeNoCheck addNode(String id, String text) {
    return addNode(id, text, null);
  }

  /**
   * 增加节点
   * 
   * @param id
   *          节点ID
   * @param text
   *          节点文本
   * @param parentNode
   *          如果为null，将默认为根节点
   * @return TreeNodeNoCheck
   */
  public TreeNodeNoCheck addNode(String id, String text, TreeNodeNoCheck parentNode) {
    TreeNodeNoCheck node = new TreeNodeNoCheck();
    node.setId(id);
    node.setText(text);
    if (null == parentNode || parentNode.equals(rootNode)) {
      rootNode.addChildren(node);
    } else {
      parentNode.addChildren(node);
    }
    flag = false;

    return node;
  }

  /**
   * 以json方式返回对象
   * 
   * @return Exception
   */
  public String toJsonString() throws Exception {
    JSONObject jsonObject = new JSONObject();
    return jsonObject.fromObject(rootNode).toString();
  }

  /**
   * 将List&lt;TreeNodeNoCheckList&gt;转换为SimpleTree对象。
   * 
   * @param treeNodeNoCheckList
   *          树节点列表
   * @param rootNodeId
   *          根节点
   * @return SimpleTreeNoCheck树
   */
  public static SimpleTreeNoCheck valueOf(List<DefaultTreeNodeNoCheck> treeNodeNoCheckList, String rootNodeId)
      throws Exception {
    if (null == treeNodeNoCheckList || treeNodeNoCheckList.isEmpty() || null == rootNodeId) {
      return null;
    }

    DefaultTreeNodeNoCheck rootNode = findRootNode(treeNodeNoCheckList, rootNodeId);// 根节点
    if (null != rootNode) {
      treeNodeNoCheckList.remove(rootNode);

      SimpleTreeNoCheck simpleTree = new SimpleTreeNoCheck(rootNode.getId(), rootNode.getText());
      List<DefaultTreeNodeNoCheck> whileList = new ArrayList<DefaultTreeNodeNoCheck>();
      whileList.addAll(treeNodeNoCheckList);
      List<DefaultTreeNodeNoCheck> swapList = new ArrayList<DefaultTreeNodeNoCheck>();
      swapList.addAll(treeNodeNoCheckList);
      List<TreeNodeNoCheck> parentList = new ArrayList<TreeNodeNoCheck>();
      List<TreeNodeNoCheck> swapParentList = new ArrayList<TreeNodeNoCheck>();
      parentList.add(simpleTree.rootNode);
      while (parentList.size() > 0 && !whileList.isEmpty()) { // 必须是并且关系，否则会死循环（因为里面有的节点可能根本就没有对应的父节点）
        for (int j = 0; j < parentList.size(); j++) {
          TreeNodeNoCheck parentNode = parentList.get(j);
          String parentNodeId = parentNode.getId();
          for (int i = 0; i < whileList.size(); i++) {
            DefaultTreeNodeNoCheck tmpNode = whileList.get(i);
            if (parentNodeId.equals(tmpNode.getParentId())) {
              TreeNodeNoCheck node = simpleTree.addNode(tmpNode.getId(), tmpNode.getText(), parentNode);
              swapList.remove(tmpNode);
              swapParentList.add(node);
            }
          }
          whileList = new ArrayList<DefaultTreeNodeNoCheck>();
          whileList.addAll(swapList);
          if ((whileList.isEmpty())) {
            return simpleTree;
          }
        }
        parentList = new ArrayList<TreeNodeNoCheck>();
        parentList.addAll(swapParentList);
        swapParentList = new ArrayList<TreeNodeNoCheck>();
      }
      return simpleTree;
    }
    return null;
  }

  /**
   * 寻找根节点
   * 
   * @param treeNodeNoCheckList
   *          树节点列表
   * @param rootNodeId
   *          根节点
   * @return DefaultTreeNodeNoCheck
   */
  private static DefaultTreeNodeNoCheck findRootNode(List<DefaultTreeNodeNoCheck> treeNodeNoCheckList,
      String rootNodeId) {
    for (int i = 0; i < treeNodeNoCheckList.size(); i++) {
      DefaultTreeNodeNoCheck tmpNode = treeNodeNoCheckList.get(i);
      if (tmpNode.getId().equals(rootNodeId)) {
        return tmpNode;
      }
    }
    return null;
  }

  /**
   * 创建DefaultTreeNode树
   * 
   * @param id
   *          id
   * @param text
   *          节点文本
   * @param parentId
   *          父ID
   * @return DefaultTreeNodeNoCheck树
   */
  public static DefaultTreeNodeNoCheck createNew(String id, String text, String parentId) {
    DefaultTreeNodeNoCheck result = new DefaultTreeNodeNoCheck();
    result.setId(id);
    result.setText(text);
    result.setParentId(parentId);
    return result;
  }
}