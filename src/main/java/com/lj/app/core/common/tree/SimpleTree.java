package com.lj.app.core.common.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 
 * 简单树
 *
 */
public class SimpleTree {
  private TreeNode rootNode;
  private boolean flag;// true:已处理过；false:未处理过

  /**
   * 唯一构造函数
   * 
   * @param rootId
   *          根节点
   * @param rootText
   *          根节点文本
   * @param checked
   *          值为：0,1,2，如果非法，默认为是0
   */
  public SimpleTree(String rootId, String rootText, int checked) {
    rootNode = new TreeNode();
    rootNode.setId(rootId);
    rootNode.setText(rootText);
    rootNode.setChecked(checked);
    flag = false;
  }

  /**
   * 增加节点，默认父节点为根节点
   * 
   * @param id
   *          节点ID
   * @param text
   *          节点文本
   * @return 树
   */
  public TreeNode addNode(String id, String text, int checked) {
    return addNode(id, text, checked, null);
  }

  /**
   * 增加节点
   * 
   * @param id
   *          节点ID
   * @param text
   *          节点文本
   * @param checked
   *          值为：0,1,2，如果非法，默认为是0
   * @param parentNode
   *          如果为null，将默认为根节点
   * @return 树
   */
  public TreeNode addNode(String id, String text, int checked, TreeNode parentNode) {
    TreeNode node = new TreeNode();
    node.setId(id);
    node.setText(text);
    node.setChecked(getChecked(checked));
    if (null == parentNode || parentNode.equals(rootNode)) {
      rootNode.addChildren(node);
    } else {
      parentNode.addChildren(node);
    }
    flag = false;

    return node;
  }

  private int getChecked(int checked) {
    switch (checked) {
      case 0:
        return 0;
      case 1:
        return 1;
      case 2:
        return 2;
      default:
        return 0;// 默认为0
    }
  }

  /**
   * 检查处理
   */
  public void processChecked() {
    if (!flag) {
      processChecked(rootNode, true);
      flag = true;
    }
  }

  private void processChecked(TreeNode treeNode, boolean isCheckChildren) {
    if (null == treeNode) {
      return;
    }

    List<TreeNode> children = treeNode.getChildren();
    // 如果是叶节点
    if (null == children || children.isEmpty()) {
      processCheckedByChildren(treeNode);
    } else {
      if (isCheckChildren) {
        for (int i = 0; i < children.size(); i++) {
          TreeNode node = children.get(i);
          if (null == node.getChildren() || node.getChildren().isEmpty()) {
            processCheckedByChildren(node);
          } else {
            processChecked(node, true);
          }
        }
        processCheckedByChildren(treeNode);
      } else {
        processCheckedByChildren(treeNode);
      }
    }
  }

  private void processCheckedByChildren(TreeNode treeNode) {
    List<TreeNode> children = treeNode.getChildren();
    // 如果是叶节点
    if (null == children || children.isEmpty()) {
      int checked = getChecked(treeNode.getChecked());
      checked = 2 == checked ? 1 : checked;// 如果是2要改为1
      treeNode.setChecked(checked);
    } else {
      int count0 = 0;
      int count1 = 0;
      List<TreeNode> list = treeNode.getChildren();
      for (int i = 0; i < list.size(); i++) {
        TreeNode node = list.get(i);
        int checked = node.getChecked();
        if (2 == checked) {
          treeNode.setChecked(2);
          return;
        } else if (0 == checked) {
          count0++;
        } else {
          count1++;
        }
      }
      if (count0 == list.size()) {
        treeNode.setChecked(0);
      } else if (count1 == list.size()) {
        treeNode.setChecked(1);
      } else {
        treeNode.setChecked(2);
      }
    }
  }

  /**
   * 以json方式返回对象
   * 
   * @return String 以json方式返回对象
   * @throws Exception
   *           异常信息
   */
  public String toJsonString() throws Exception {
    JSONObject jsonObject = new JSONObject();
    return jsonObject.fromObject(rootNode).toString();
  }

  /**
   * 将List&lt;treeNodeList&gt;转换为SimpleTree对象。
   * 
   * @param treeNodeList
   *          树节点列表
   * @param rootNodeId
   *          根节点
   * @return SimpleTree树
   */
  public static SimpleTree valueOf(List<DefaultTreeNode> treeNodeList, String rootNodeId) throws Exception {
    if (null == treeNodeList || treeNodeList.isEmpty() || null == rootNodeId) {
      return null;
    }

    DefaultTreeNode rootNode = findRootNode(treeNodeList, rootNodeId);// 根节点
    if (null != rootNode) {
      treeNodeList.remove(rootNode);

      SimpleTree simpleTree = new SimpleTree(rootNode.getId(), rootNode.getText(), rootNode.getChecked());
      List<DefaultTreeNode> whileList = new ArrayList<DefaultTreeNode>();
      whileList.addAll(treeNodeList);
      List<DefaultTreeNode> swapList = new ArrayList<DefaultTreeNode>();
      swapList.addAll(treeNodeList);
      List<TreeNode> parentList = new ArrayList<TreeNode>();
      List<TreeNode> swapParentList = new ArrayList<TreeNode>();
      parentList.add(simpleTree.rootNode);
      while (parentList.size() > 0 && !whileList.isEmpty()) { // 必须是并且关系，否则会死循环（因为里面有的节点可能根本就没有对应的父节点）
        for (int j = 0; j < parentList.size(); j++) {
          TreeNode parentNode = parentList.get(j);
          String parentNodeId = parentNode.getId();
          for (int i = 0; i < whileList.size(); i++) {
            DefaultTreeNode tmpNode = whileList.get(i);
            if (parentNodeId.equals(tmpNode.getParentId())) {
              TreeNode node = simpleTree.addNode(tmpNode.getId(), tmpNode.getText(), tmpNode.getChecked(), parentNode);
              swapList.remove(tmpNode);
              swapParentList.add(node);
            }
          }
          whileList = new ArrayList<DefaultTreeNode>();
          whileList.addAll(swapList);
          if ((whileList.isEmpty())) {
            return simpleTree;
          }
        }
        parentList = new ArrayList<TreeNode>();
        parentList.addAll(swapParentList);
        swapParentList = new ArrayList<TreeNode>();
      }
      return simpleTree;
    }
    return null;
  }

  /**
   * 寻找根节点
   * 
   * @param treeNodeList
   *          树节点列表
   * @param rootNodeId
   *          根节点
   * @return DefaultTreeNode树
   */
  private static DefaultTreeNode findRootNode(List<DefaultTreeNode> treeNodeList, String rootNodeId) {
    for (int i = 0; i < treeNodeList.size(); i++) {
      DefaultTreeNode tmpNode = treeNodeList.get(i);
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
   * @param checked
   *          是否选中
   * @param parentId
   *          父ID
   * @return DefaultTreeNode树
   */
  public static DefaultTreeNode createNew(String id, String text, int checked, String parentId) {
    DefaultTreeNode result = new DefaultTreeNode();
    result.setId(id);
    result.setText(text);
    result.setChecked(checked);
    result.setParentId(parentId);
    return result;
  }
}