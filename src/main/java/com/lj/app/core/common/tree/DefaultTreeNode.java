package com.lj.app.core.common.tree;

/**
 * 
 * 默认树节点
 *
 */
public class DefaultTreeNode {
  private String id;
  private String text;
  private int checked;
  private String parentId;

  public int getChecked() {
    return checked;
  }

  public void setChecked(int checked) {
    this.checked = checked;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
