package com.lj.app.core.common.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BootStrapTreeView {

	private String id;
	private String text;
	private String icon;
	
	private int checked;
	private String parentId;
	
	//checked: true,
    //disabled: true,
    //expanded: true,
    //selected: true
	private Map<String,Boolean> state = new HashMap<String,Boolean>();
	
	private List<BootStrapTreeView> nodes;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Map<String, Boolean> getState() {
		return state;
	}

	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}

	public List<BootStrapTreeView> getNodes() {
		return nodes;
	}

	public void setNodes(List<BootStrapTreeView> nodes) {
		this.nodes = nodes;
	}
	
	public void addNodes(BootStrapTreeView childrenNode) {
		if (null == nodes) {
			nodes = new ArrayList<BootStrapTreeView>();
		}
		nodes.add(childrenNode);
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
