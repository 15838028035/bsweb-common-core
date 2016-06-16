package com.lj.app.core.common.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeNoCheck {
	private String id;

	private String text;

	private List<TreeNodeNoCheck> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void addChildren(TreeNodeNoCheck childrenNode) {
		if (null == children) {
			children = new ArrayList<TreeNodeNoCheck>();
		}
		children.add(childrenNode);
	}

	public List<TreeNodeNoCheck> getChildren() {
		return children;
	}
}